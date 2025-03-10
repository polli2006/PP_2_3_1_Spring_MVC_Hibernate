    package web.dao;

    import org.springframework.stereotype.Repository;
    import org.springframework.transaction.annotation.Transactional;
    import web.model.User;

    import javax.persistence.EntityManager;
    import javax.persistence.PersistenceContext;
    import java.util.List;

    @Repository
    @Transactional
    public class UserDaoImpl implements UserDao {

        @PersistenceContext
        private EntityManager entityManager;

        @Override
        public User getUser(int id) {
            return entityManager.find(User.class, id);
        }

        @Override
        public void deleteUser(int id) {
            User user = getUser(id);
            if (user != null) {
                entityManager.remove(user); // Удаление пользователя
            }
        }

        @Override
        public void addUser(User user) {
            entityManager.merge(user);
        }

        @Override
        public void updateUser(User user) {
            entityManager.merge(user);
        }

        public List<User> listUsers() {
            return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
        }

    }
