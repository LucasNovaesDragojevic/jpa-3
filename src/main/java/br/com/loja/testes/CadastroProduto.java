package br.com.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.loja.JPAUtil;
import br.com.loja.dao.CategoriaDao;
import br.com.loja.dao.ProdutoDao;
import br.com.loja.modelo.Categoria;
import br.com.loja.modelo.Produto;

public class CadastroProduto {

    public static void main(String[] args) {
        Categoria categoria = new Categoria("Celular");

        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), categoria);

        EntityManager em = JPAUtil.getEntityManager();
        
        ProdutoDao produtoDao = new ProdutoDao(em);

        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(categoria);

        produtoDao.cadastrar(celular);
        
        em.getTransaction().commit();
        
        em.close();
    }
}
