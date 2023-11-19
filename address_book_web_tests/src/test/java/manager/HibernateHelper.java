package manager;

import io.qameta.allure.Step;
import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase {

    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);

        sessionFactory = new Configuration()
                .addAnnotatedClass(ContactRecord.class)
                .addAnnotatedClass(GroupRecord.class)
                // PostgreSQL
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                // Credentials
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")

                // Create a new SessionFactory
                .buildSessionFactory();
    }


    static List<GroupData> convertGroupList(List<GroupRecord> records) {
        return records.stream().map(groupRecord -> convert(groupRecord)).collect(Collectors.toList());
//        implementation using loop:
//        var result = new ArrayList<GroupData>();
//        for (var record : records) {
//            result.add(convert(record));
//        }
//        return result;
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData(String.valueOf(record.id), record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if (id.equals("")) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    static List<ContactData> convertContactList(List<ContactRecord> records) {
        return records.stream().map(contactRecord -> convert(contactRecord)).collect(Collectors.toList());
//        implementation using loop:
//        var result = new ArrayList<ContactData>();
//        for (var record : records) {
//            result.add(convert(record));
//        }
//        return result;
    }

    private static ContactData convert(ContactRecord record) {
        return new ContactData()
                .withId(String.valueOf(record.id))
                .withFirstName(record.firstName)
                .withMiddleName(record.middleName)
                .withLastName(record.lastName)
                .withCompany(record.company)
                .withAddress(record.address)
                .withHomePhone(record.homePhone)
                .withMobilePhone(record.mobilePhone)
                .withWorkPhone(record.workPhone)
                .withEmail(record.email)
                .withEmail2(record.email2)
                .withEmail3(record.email3)
                .withSecondaryPhone(record.secondaryPhone);
    }

    private static ContactRecord convert(ContactData data) {
        var id = data.id();
        if (id.equals("")) {
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), data.firstName(), data.middleName(), data.lastName(), data.company(), data.address(), data.homePhone(), data.mobilePhone(), data.workPhone(), data.email(), data.email2(), data.email3(), data.secondaryPhone());
    }


    @Step
    public List<GroupData> getGroupList() {
        return convertGroupList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }


    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });
    }

    @Step
    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
        });
    }

    @Step
    public List<ContactData> getContactsList() {
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public void createContact(ContactData contactData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(contactData));
            session.getTransaction().commit();
        });
    }
}
