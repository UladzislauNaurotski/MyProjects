import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();

        String hql = "SELECT student.id, course.id from " + PurchaseList.class.getSimpleName() + " as purchaseList" +
                " inner join " + Student.class.getSimpleName() + " as student on "
                + "purchaseList.studentName = student.name " +
                "inner join " + Course.class.getSimpleName() + " as course on " +
                "purchaseList.courseName = course.name";

        List<Object[]> purchaseList = session.createQuery(hql).getResultList();
        List<LinkedPurchaseList> linkedPurchaseLists = new ArrayList<>();
        for (Object[] p : purchaseList) {
            LinkedPurchaseListKey key = new LinkedPurchaseListKey();
            key.setStudentId((Integer) p[0]);
            key.setCourseId((Integer) p[1]);
            LinkedPurchaseList list = new LinkedPurchaseList();
            list.setId(key);
            linkedPurchaseLists.add(list);
            System.out.println("student.id " + list.getId().getStudentId() +
                    " course.id " + list.getId().getCourseId());
        }
        System.out.println(linkedPurchaseLists.size());
        session.close();
    }
}
