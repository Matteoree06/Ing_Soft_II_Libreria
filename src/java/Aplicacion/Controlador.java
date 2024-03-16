/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion;

import DAO.ClienteDAO;
import DAO.CompraDAO;
import Datos.Carrito;
import Datos.Producto;
import DAO.ProductoDAO;
import Datos.Cliente;
import Datos.Compra;
import Datos.Pago;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mateo
 */
public class Controlador extends HttpServlet {

    Cliente cl = new Cliente();
    ClienteDAO cldao = new ClienteDAO();
    ProductoDAO pdao=new ProductoDAO();
    Producto p=new Producto();
    List<Producto> productos=new ArrayList();
    
    List<Carrito> listaCarrito=new ArrayList();
    String logueo = "Iniciar Sesion";
    String correo = "Iniciar Sesion";
    int item;
    double totalPagar=0.0;
    int cantidad=1;
    
    int idp;
    Carrito car;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("logueo", logueo);
        session.setAttribute("correo", correo);
        productos=pdao.listar();
        String accion=request.getParameter("accion");
        switch(accion){
            case "Comprar":
                totalPagar=0.0;
                idp=Integer.parseInt(request.getParameter("id"));
                p=pdao.listarId(idp);
                item=item+1;
                car=new Carrito();
                car.setItem(item);
                car.setIdProducto(p.getId());
                car.setNombres(p.getNombres());
                car.setDescripcion(p.getDescripcion());
                car.setPrecioCompra(p.getPrecio());
                car.setCantidad(cantidad);
                car.setSubTotal(cantidad*p.getPrecio());
                listaCarrito.add(car);
                for (int i = 0; i < listaCarrito.size(); i++) {
                    totalPagar = totalPagar + listaCarrito.get(i).getSubTotal();
                }
                request.setAttribute("totalPagar", totalPagar);
                request.setAttribute("carrito", listaCarrito);
                request.setAttribute("contador", listaCarrito.size());
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                
                break;
            case "AgregarCarrito":
                int pos=0;
                cantidad = 1;
                idp=Integer.parseInt(request.getParameter("id"));
                p=pdao.listarId(idp);
                if(listaCarrito.size()>0){
                    for(int i = 0; i < listaCarrito.size(); i++){
                        if(idp==listaCarrito.get(i).getIdProducto()){
                            pos=i;
                        }
                    }
                    if(idp==listaCarrito.get(pos).getIdProducto()){
                        cantidad=listaCarrito.get(pos).getCantidad()+cantidad;
                        double subtotal=listaCarrito.get(pos).getPrecioCompra()*cantidad;
                        listaCarrito.get(pos).setCantidad(cantidad);
                        listaCarrito.get(pos).setSubTotal(subtotal);
                    }else{
                        item = item + 1;
                        car = new Carrito();
                        car.setItem(item);
                        car.setIdProducto(p.getId());
                        car.setNombres(p.getNombres());
                        car.setDescripcion(p.getDescripcion());
                        car.setPrecioCompra(p.getPrecio());
                        car.setCantidad(cantidad);
                        car.setSubTotal(cantidad * p.getPrecio());
                        listaCarrito.add(car);
                    }
                }else{
                    item = item + 1;
                    car = new Carrito();
                    car.setItem(item);
                    car.setIdProducto(p.getId());
                    car.setNombres(p.getNombres());
                    car.setDescripcion(p.getDescripcion());
                    car.setPrecioCompra(p.getPrecio());
                    car.setCantidad(cantidad);
                    car.setSubTotal(cantidad * p.getPrecio());
                    listaCarrito.add(car);
                }
                request.setAttribute("contador", listaCarrito.size());
                request.getRequestDispatcher("Controlador?accion=home").forward(request, response);
                break;
            case "Delete":
                idp= Integer.parseInt(request.getParameter("id"));
                if (listaCarrito != null) {
                    for (int j = 0; j < listaCarrito.size(); j++) {
                        if (listaCarrito.get(j).getIdProducto() == idp) {
                            listaCarrito.remove(j);
                        }
                    }
                }
                break;
            case "ActualizarCantidad":
                int idpro=Integer.parseInt(request.getParameter("idp"));
                int cant=Integer.parseInt(request.getParameter("Cantidad"));
                for (int i = 0; i < listaCarrito.size(); i++) {
                    if (listaCarrito.get(i).getIdProducto() == idpro) {
                    listaCarrito.get(i).setCantidad(cant);
                    double st=listaCarrito.get(i).getPrecioCompra()*cant;
                    listaCarrito.get(i).setSubTotal(st);
                    }
                }
                break;
             case "Carrito":
                totalPagar=0;
                request.setAttribute("carrito", listaCarrito);
                for(int i = 0; i < listaCarrito.size(); i++){
                    totalPagar=totalPagar+listaCarrito.get(i).getSubTotal();
                }
                request.setAttribute("totalPagar", totalPagar);
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;
             case "Validar":
                String email = request.getParameter("txtemail");
                String pass = request.getParameter("txtpass");
                cl = cldao.Validar(email, pass);
                if (cl.getId() != 0) {
                    logueo = cl.getNombres();
                    correo = cl.getEmail();
                }
                request.getRequestDispatcher("Controlador?accion=carrito").forward(request, response);
                break;
            case "Registrar":
                String nom = request.getParameter("txtnom");
                String dni = request.getParameter("txtdni");
                String em = request.getParameter("txtemail");
                String pas = request.getParameter("txtpass");
                String dir = request.getParameter("txtdire");
                cl.setNombres(nom);
                cl.setDni(dni);
                cl.setEmail(em);
                cl.setPass(pas);
                cl.setDireccion(dir);
                cldao.AgregarCliente(cl);
                request.getRequestDispatcher("Controlador?accion=carrito").forward(request, response);
                break;
             case "GenerarCompra":
                 Cliente cliente=new Cliente();
                 cliente.setId(12);
                 CompraDAO dao=new CompraDAO();
                 Compra compra=new Compra(cliente, 19, Fecha.FechaBD(), totalPagar, "Cancelado", listaCarrito);
                 int res=dao.GenerarCompra(compra);
                 if(res!=0&&totalPagar>0){
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                 }else{
                     request.getRequestDispatcher("error.jsp").forward(request, response);
                 }
                 break;
            default:
            request.setAttribute("productos", productos);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
