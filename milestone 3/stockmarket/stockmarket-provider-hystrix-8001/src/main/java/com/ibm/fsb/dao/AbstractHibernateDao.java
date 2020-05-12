package com.ibm.fsb.dao;

import java.io.BufferedReader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.internal.SessionFactoryImpl;

/**
 * Base class for DAOs which need Hibernate specific features
 * @param <E> the entity type
 * @param <K> the primary key type
 */
public abstract class AbstractHibernateDao<E, K> extends AbstractJpaDao<E, K> {

	/**
     * logging interface
     */
    private static final Logger LOG = LogManager.getLogger(AbstractHibernateDao.class);
    /**
     * Constructor
     * @param em the entity manager
     */
    public AbstractHibernateDao(EntityManager em) {
        super(em);
    }

    /**
     * @return a Criteria instance
     */
    protected CriteriaQuery<E> createCriteria() {
        Session session = (Session) em.getDelegate();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        return builder.createQuery(entityClass);
    }
    

    public String getDefaultSchema() {
        Connection conn = null;
        Session session = (Session) em.getDelegate();
        SessionFactoryImpl sessionFactory = (SessionFactoryImpl) session.getSessionFactory();
        try {
            SessionImplementor sessionImplementor = (SessionImplementor) sessionFactory.getCurrentSession();
            conn = sessionImplementor.getJdbcConnectionAccess().obtainConnection();
            String url = conn.getMetaData().getURL();
            return url.substring(url.lastIndexOf('/') + 1);
        } catch (Exception e) {
            LOG.debug(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                LOG.debug(e.getMessage());
            }
        }
        return "cloudDB01";
    }

    public int executeUpdate(String sql, Object[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Session session = (Session) em.getDelegate();
        SessionFactoryImpl sessionFactory = (SessionFactoryImpl) session.getSessionFactory();
        try {
            SessionImplementor sessionImplementor = (SessionImplementor) sessionFactory.getCurrentSession();
            conn = sessionImplementor.getJdbcConnectionAccess().obtainConnection();
            LOG.info("********************* update sql: " + sql);
            stmt = conn.prepareStatement(sql);
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    stmt.setObject(i + 1, args[i]);
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("parameter" + i + ":" + args[i].toString());
                    }
                }
            } else {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("args is null");
                }
            }
            return stmt.executeUpdate();
        } catch (Exception e) {
            LOG.debug(e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                LOG.debug(e.getMessage());
            }
        }
        return -1;
    }

    public List<Map<String, Object>> executeQuery(String sql, Object[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Map<String, Object>> arrayList = new ArrayList<>();
        Session session = (Session) em.getDelegate();
        SessionFactoryImpl sessionFactory = (SessionFactoryImpl) session.getSessionFactory();
        try {
            SessionImplementor sessionImplementor = (SessionImplementor) sessionFactory.getCurrentSession();
            conn = sessionImplementor.getJdbcConnectionAccess().obtainConnection();
            pstmt = conn.prepareStatement(sql);
            LOG.info("********************* query sql: " + sql);
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    pstmt.setObject(i + 1, args[i]);
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("parameter" + i + ":" + args[i].toString());
                    }
                }
            } else {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("args is null");
                }
            }
            rs = pstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                Map<String, Object> resultSet = new HashMap<>();
                for (int j = 0; j < rsmd.getColumnCount(); j++) {
                    if (rsmd.getColumnTypeName(j + 1).equals("TEXT")) {
                        Clob clob = rs.getClob(j + 1);
                        BufferedReader br = new BufferedReader(clob.getCharacterStream());
                        int len = (int) clob.length();
                        char[] temp = new char[len];
                        int i = 1;
                        while ((i = br.read(temp, 0, temp.length)) != -1) {
                            i = br.read(temp, 0, i);
                        }
                        resultSet.put(rsmd.getColumnName(j + 1), rs.getObject(j + 1) == null
                                ? "" : new String(temp));
                    } else {
                        resultSet.put(rsmd.getColumnName(j + 1), rs.getObject(j + 1));
                    }
                }
                arrayList.add(resultSet);
            }
        } catch (Exception e) {
            LOG.debug(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                LOG.debug(ex.getMessage());
            }
        }
        return arrayList;
    }
}	
