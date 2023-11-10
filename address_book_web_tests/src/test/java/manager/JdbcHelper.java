package manager;

import model.GroupData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase {
    public JdbcHelper(ApplicationManager manager) {
        super(manager);
    }


    public List<GroupData> getGroupList() {

        var groups = new ArrayList<GroupData>();
        try (var connection = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = connection.createStatement();
             var result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list"))
        {
            while (result.next()) {
                groups.add(new GroupData()
                        .withId(result.getString("group_id"))
                        .withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    public void checkConsistency() {
        try (var connection = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = connection.createStatement();
             var result = statement.executeQuery(
                     "SELECT * FROM address_in_groups ag LEFT JOIN addressbook a ON ag.id=a.id WHERE a.id IS NULL"))
        {
            if (result.next()) {
                throw new IllegalStateException("Database is corrupted");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}