package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CarDaoImpl implements CarDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.openSession().save(car);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserFromModelAndSeries(String model, int series) {
        String HQL = "from User u JOIN FETCH u.car c WHERE c.series=:series AND c.model=:model";
        return sessionFactory.openSession()
                .createQuery(HQL, User.class)
                .setParameter("series", series)
                .setParameter("model", model)
                .getSingleResult();
    }
}
