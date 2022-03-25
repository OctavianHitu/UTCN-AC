package Businesslayer;

import model.Account;
import model.Baseproduct;
import model.Order;

import java.util.BitSet;
import java.util.List;

public interface IDeliveryServiceProcessing {

    /**
     * Citeste produsele din fisierul products.csv si face serializare dupa
     * @post formedwell()
     */

    void readproduct();

    /**
     * lista de produse din meniu este returnata
     * @return produse
     */
    List<Baseproduct> getproduct();

    /**
     * adauga un produs in lista
     * @param p
     * @post  formedwell()
     */
    void addbaseproduct(Baseproduct p);

    /**
     * Face update la lista de produse din meniu
     * @prea p != null
     * @param p reprezinta noua lista cu produse
     * @post formedwell()
     */
    void setproduct(List<Baseproduct> p);

    /**
     * sterge un produs din lista
     * @pre p!=null
     * @param p
     * @post formedwell()
     */
    void deletebaseproduct(Baseproduct p);

    /**
     * adauga un client in lista de clienti
     * @pre acc!=null
     * @param acc elementul pe care-l adauga
     * @post formedwell()
     */
    void addaccount(Account acc);

    /**
     * salveaza o comanda compusa din mai multe produse
     * @pre o!=null && m!=null
     * @param o
     * @param m
     * @post formedwell()
     */
    void addmaporderlist(Order o, List<Baseproduct> m);

    /**
     * returneaza lista cu orders
     * @return orderlist
     */
    List<Order> getOrderlist();

    /**
     * adauga o comanda in lista de comenzi
     * @pre 0!=null
     * @param o
     * @post formedwell()
     */
    void addsimpleorder(Order o);

    /**
     * cauta un client in lista
     * @param a
     * @return exist
     */
    boolean searchaccount(Account a);

    /**
     * porneste frame-ul pentru employee
     * @param ord
     */
    void addemployeeobs(Order ord);

    /**
     * returneaza lista de clienti
     * @return accountlist
     */
    List<Account> getaccountlist();

    /**
     *  folosim aceasta pentru post conditii
     * @return
     */
    boolean formedwell();








}
