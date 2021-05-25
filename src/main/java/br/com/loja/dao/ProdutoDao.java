package br.com.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.loja.modelo.Produto;

public class ProdutoDao {
    
    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {
        this.em.persist(produto);
    }

    public Produto buscarPorId(Long id) {
        return em.find(Produto.class, id);
    }

    public List<Produto> buscarTodos() {
        String jpql = "select p from Produto p";
        return em.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> buscarPorNome(String nome) {
        String jpql = "select p from Produto p where p.nome = :pNome";
        return em.createQuery(jpql, Produto.class)
                    .setParameter("pNome", nome)
                    .getResultList();
    }

    public List<Produto> buscarPorNomeDaCategoria(String nome) {
        String jpql = "select p from Produto p where p.categoria.nome = :pNome";
        return em.createQuery(jpql, Produto.class)
                    .setParameter("pNome", nome)
                    .getResultList();
    }

    public BigDecimal buscarPrecoDoProdutoComNome(String nome) {
        String jpql = "select p.preco from Produto p where p.nome = :pNome";
        return em.createQuery(jpql, BigDecimal.class)
                    .setParameter("pNome", nome)
                    .getSingleResult();
    }
}
