/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Aplicacion.Conexion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mateo
 */
public class ProductoDAO {
    Connection con;
    Conexion cn=new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
        List<Producto>productos=new ArrayList();
        String sql="select * from productos";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                Producto p=new Producto();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setFoto(rs.getBinaryStream(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
                productos.add(p);
            }
    }catch (Exception e){
    
    }
    return productos;        
    }
    public void listarImg(int id, HttpServletResponse response){
        String sql="select * from producto where idProducto="+id;
        InputStream inputStream=null;
        OutputStream outputStream=null;
        BufferedInputStream bufferedInputStream=null;
        BufferedOutputStream bufferedOuputStream=null;
        try{
            outputStream=response.getOutputStream();
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            if(rs.next()){
                inputStream=rs.getBinaryStream("Foto");
            }
            bufferedInputStream=new BufferedInputStream(inputStream);
            bufferedOuputStream=new BufferedOutputStream(outputStream);
            int i=0;
            while((i=bufferedInputStream.read())!=-1){
                bufferedOuputStream.write(i);
            }
        } catch (Exception e){
    
    }
    }
}
