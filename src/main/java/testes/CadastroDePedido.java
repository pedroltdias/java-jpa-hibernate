package testes;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ClienteDAO;
import br.com.alura.loja.dao.PedidoDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido {

    public static void main(String[] args) {
        popularBD();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);

        Produto produto = produtoDAO.buscarPorId(1l);
        Cliente cliente = clienteDAO.buscarPorId(1l);

        em.getTransaction().begin();

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));

        PedidoDAO pedidoDAO = new PedidoDAO(em);
        pedidoDAO.cadastrar(pedido);

        em.getTransaction().commit();

        BigDecimal totalVendido = pedidoDAO.valorTotalVendido();
        System.out.println("Total Vendido: " + totalVendido);

        List<Object[]> relatorio = pedidoDAO.relatorioDeVendas();
        for (Object[] obj : relatorio) {
            System.out.println(obj[0]);
            System.out.println(obj[1]);
            System.out.println(obj[2]);
        }

    }

    private static void popularBD() {
        Categoria celulares = new Categoria("Celulares");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal",
                new BigDecimal("800"), celulares);

        Cliente cliente = new Cliente("Cliente", "123456");

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);

        em.getTransaction().begin();

        categoriaDAO.cadastrar(celulares);
        produtoDAO.cadastrar(celular);
        clienteDAO.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
    }
}
