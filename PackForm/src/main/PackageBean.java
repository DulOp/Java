
package main;

import com.sun.rowset.JdbcRowSetImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.rowset.JdbcRowSet;

public class PackageBean {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/package_orders";
    static final String DB_USER = "";
    static final String DB_PASS = "";
    private JdbcRowSet rowSet;
    
    public PackageBean() {
        try {
            Class.forName(JDBC_DRIVER);
            rowSet = new JdbcRowSetImpl();
            rowSet.setUrl(DB_URL);
            rowSet.setUsername(DB_USER);
            rowSet.setPassword(DB_PASS);
            rowSet.setCommand("SELECT * FROM package");
            rowSet.execute();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public Package create (Package p) {
        try {
            rowSet.moveToInsertRow();
            rowSet.updateInt("packageId", p.getPackageId());
            rowSet.updateString("speed", p.getSpeed());
            rowSet.updateString("flow", p.getFlow());
            rowSet.updateString("contract_length", p.getContractLength());
            rowSet.updateString("first_name", p.getFirstName());
            rowSet.updateString("last_name", p.getLastName());
            rowSet.updateString("address", p.getAddress());
            rowSet.insertRow();
            rowSet.moveToCurrentRow();
        } catch (SQLException e) {
            try {
                rowSet.rollback();
                p = null;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return p;
    }
    
    public Package update(Package p) {
        try {
            rowSet.updateString("speed", p.getSpeed());
            rowSet.updateString("flow", p.getFlow());
            rowSet.updateString("contract_length", p.getContractLength());
            rowSet.updateString("first_name", p.getFirstName());
            rowSet.updateString("last_name", p.getLastName());
            rowSet.updateString("address", p.getAddress());
            rowSet.updateRow();
            rowSet.moveToCurrentRow();
        } catch (SQLException e) {
            try {
                rowSet.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return p;
    }
    
    public void delete() {
        try {
            rowSet.moveToCurrentRow();
            rowSet.deleteRow();
        } catch (SQLException ex) {
            try {
                rowSet.rollback();
            } catch (SQLException e) {
                ex.printStackTrace();
            }
        }
    }
    
    public Package moveFirst() {
        Package p = new Package();
        try {
            rowSet.first();
            p.setPackageId(rowSet.getInt("packageId"));
            p.setSpeed(rowSet.getString("speed"));
            p.setFlow(rowSet.getString("flow"));
            p.setContractLength(rowSet.getString("contract_length"));
            p.setFirstName(rowSet.getString("first_name"));
            p.setLastName(rowSet.getString("last_name"));
            p.setAddress(rowSet.getString("address"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }
    
    public Package moveLast() {
        Package p = new Package();
        try {
            rowSet.last();
            p.setPackageId(rowSet.getInt("packageId"));
            p.setSpeed(rowSet.getString("speed"));
            p.setFlow(rowSet.getString("flow"));
            p.setContractLength(rowSet.getString("contract_length"));
            p.setFirstName(rowSet.getString("first_name"));
            p.setLastName(rowSet.getString("last_name"));
            p.setAddress(rowSet.getString("address"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }
    
    public Package moveNext() {
        Package p = new Package();
        try {
            if (rowSet.next() == false) {
                rowSet.previous();
            }
            p.setPackageId(rowSet.getInt("packageId"));
            p.setSpeed(rowSet.getString("speed"));
            p.setFlow(rowSet.getString("flow"));
            p.setContractLength(rowSet.getString("contract_length"));
            p.setFirstName(rowSet.getString("first_name"));
            p.setLastName(rowSet.getString("last_name"));
            p.setAddress(rowSet.getString("address"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }
    
    public Package movePrevious() {
        Package p = new Package();
        try {
            if (rowSet.previous() == false) {
                rowSet.next();
            }
            p.setPackageId(rowSet.getInt("packageId"));
            p.setSpeed(rowSet.getString("speed"));
            p.setFlow(rowSet.getString("flow"));
            p.setContractLength(rowSet.getString("contract_length"));
            p.setFirstName(rowSet.getString("first_name"));
            p.setLastName(rowSet.getString("last_name"));
            p.setAddress(rowSet.getString("address"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }
    
    public Package getCurrent() {
        Package p = new Package();
        try {
            rowSet.moveToCurrentRow();
            p.setPackageId(rowSet.getInt("packageId"));
            p.setSpeed(rowSet.getString("speed"));
            p.setFlow(rowSet.getString("flow"));
            p.setContractLength(rowSet.getString("contract_length"));
            p.setFirstName(rowSet.getString("first_name"));
            p.setLastName(rowSet.getString("last_name"));
            p.setAddress(rowSet.getString("address"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }
}
