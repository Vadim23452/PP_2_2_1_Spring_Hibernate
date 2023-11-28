package hiber.dao;

import hiber.model.User;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> getUserByModelAndSeries(String model, int series) {
      TypedQuery<User> query;
      query = sessionFactory.getCurrentSession().createQuery("from User u where u.car.model=:model and u.car.series=:series");
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.getResultList();
   }
   //"from foo Foo as foo where foo.name=:name and foo.size=:size"

}
