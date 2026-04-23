package org.example.springsamples.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.springsamples.entity.Transaction;
import jakarta.transaction.Transactional;
import org.example.springsamples.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionRepository {
    @PersistenceContext

    private EntityManager entityManager;
   @Transactional
   public void save(Transaction txn) {
       entityManager.persist(txn);
   }
   public Transaction findById(Long id) {
       return
               entityManager.find(Transaction.class,id);
   }
   public List<Transaction>
    getAllTransactions(){
       return entityManager
               .createQuery("FROM Transaction", Transaction.class)
               .getResultList();
   }
   public List<Transaction>
    findByAccount(Account account) {
       return entityManager
               .createQuery("FROM Transaction t WHERE t.account = :acc",Transaction.class )
               .setParameter("acc",account)
               .getResultList();
   }
   @Transactional
    public  void delete (long id ) {
       Transaction txn = findById(id);
       if(txn != null) {
           entityManager.remove(txn);
       }
   }
}
