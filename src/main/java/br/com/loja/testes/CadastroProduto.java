package br.com.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.loja.dao.CategoriaDao;
import br.com.loja.dao.ProdutoDao;
import br.com.loja.modelo.Categoria;
import br.com.loja.modelo.Produto;
import br.com.loja.util.JPAUtil;

public class CadastroProduto {

    public static void main(String[] args) {
        cadastrarProduto();

        EntityManager em = JPAUtil.getEntityManager();
        
        ProdutoDao produtoDao = new ProdutoDao(em);
        
        Produto produto = produtoDao.buscarPorId(1L);

        System.out.println(produto);

        produtoDao.buscarTodos().forEach(System.out::println);

        produtoDao.buscarPorNome("Xiaomi Redmi").forEach(System.out::println);

        produtoDao.buscarPorNomeDaCategoria("Celular").forEach(System.out::println);

        BigDecimal preco = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
        
        System.out.println(preco);
    }

    private static void cadastrarProduto() {
        Categoria categoria = new Categoria("Celular");

        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), categoria);

        EntityManager em = JPAUtil.getEntityManager();
        
        ProdutoDao produtoDao = new ProdutoDao(em);

        CategoriaDao categoriaDao = new CategoriaDao(em);

        // Transação 1
        em.getTransaction().begin();

        categoriaDao.cadastrar(categoria);

        produtoDao.cadastrar(celular);

        em.getTransaction().commit();

        // Transação 2
        /*em.getTransaction().begin();
        
        celular.removerCategoria();

        categoriaDao.remover(categoria);

        em.getTransaction().commit();
        */
        em.close();
    }
}
