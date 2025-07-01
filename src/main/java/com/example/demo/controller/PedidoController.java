package com.example.demo.controller;

import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.service.PedidoService;
import com.example.demo.service.ProductoService;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProductoService productoService;

    @GetMapping("/nuevo")
    public String mostrarFormularioPedido(@RequestParam("productoId") Long productoId, Model model, HttpSession session) {
        Optional<Producto> optionalProducto = productoService.buscarPorId(productoId);
        if (optionalProducto.isEmpty() || session.getAttribute("usuario") == null) {
            return "redirect:/catalogo";
    }

    Producto producto = optionalProducto.get();
    Pedido pedido = new Pedido();
    pedido.setProducto(producto);

    model.addAttribute("pedido", pedido);
    model.addAttribute("producto", producto);

    return "pedido/formulario";
}


    @PostMapping("/guardar")
    public String guardarPedido(@ModelAttribute Pedido pedido, HttpSession session) {
        Usuario cliente = (Usuario) session.getAttribute("usuario");
        if (cliente == null) return "redirect:/login";

        pedido.setCliente(cliente);
        pedido.setEstado("PENDIENTE");
        pedidoService.guardarPedido(pedido);
        return "redirect:/pedido/mis-pedidos";
    }

    @GetMapping("/mis-pedidos")
    public String misPedidos(Model model, HttpSession session,
    @RequestParam(name = "msg", required = false) String msg) {
    Usuario cliente = (Usuario) session.getAttribute("usuario");
    if (cliente == null) return "redirect:/login";

    List<Pedido> pedidos = pedidoService.obtenerPorCliente(cliente)
            .stream()
            .filter(p -> !"CANCELADO".equalsIgnoreCase(p.getEstado()))
            .toList(); // Solo pedidos que no están cancelados

    model.addAttribute("pedidos", pedidos);
    model.addAttribute("msg", msg);
    return "pedido/mis-pedidos";
}

    @GetMapping("/detalle/{id}")
    public String detallePedido(@PathVariable Long id, Model model, HttpSession session) {
        Pedido pedido = pedidoService.obtenerPorId(id).orElse(null);
        if (pedido == null || !pedido.getCliente().equals(session.getAttribute("usuario"))) {
            return "redirect:/pedido/mis-pedidos";
        }
        model.addAttribute("pedido", pedido);
        return "pedido/detalle";
    }

    @GetMapping("/cancelar/{id}")
    public String cancelarPedido(@PathVariable Long id, HttpSession session) {
        Pedido pedido = pedidoService.obtenerPorId(id).orElse(null);
        if (pedido == null || !pedido.getCliente().equals(session.getAttribute("usuario"))) {
            return "redirect:/pedido/mis-pedidos";
        }
        if (!pedido.getEstado().equals("PENDIENTE")) {
            return "redirect:/pedido/mis-pedidos"; // Solo se pueden cancelar pedidos pendientes
        }
        pedido.setEstado("CANCELADO");
        pedidoService.guardarPedido(pedido);

        return "redirect:/pedido/mis-pedidos?msg=cancelado";
    }
}