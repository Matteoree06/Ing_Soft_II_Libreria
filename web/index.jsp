<%-- 
    Document   : index
    Created on : 12/03/2024, 08:44:50 PM
    Author     : mateo
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">Mr.Books</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
              <a class="nav-link" href="Controlador?accion=home">Home <span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
              <a class="nav-link" href="#">Ofertas</a>
          </li>
          <li class="nav-item">
              <a class="nav-link" href="Controlador?accion=Carrito"><i class="fas fa-cart-plus">(<label style="color: darkorange">${contador}</label>)</i>Carrito</a>
          </li>
      </ul>
              <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                  <!--<form class="form-inline my-2 my-lg-0">-->
                  <input style="width:400px" class="form-control mr-sm-2" id="txtBuscar">
                  <button class="btn btn-outline-info my-2 my-sm-0" id="btnBuscar"><i class="fas fa-search"></i> Buscar</button>
                  <!-- </form>       -->                 
              </ul>                                
              <ul class="navbar-nav btn-group my-2 my-lg-0" role="group">
                  <a style="color: white; cursor: pointer" class="dropdown-toggle" data-toggle="dropdown">
                      <i class="fas fa-user-tie"></i> ${logueo}</a>
                  <div class="dropdown-menu text-center dropdown-menu-right">
                      <a class="dropdown-item" href="#"><img src="img/user.png" alt="60" height="60"/></a>                        
                      <a class="dropdown-item" href="#">${user}</a>
                      <a class="dropdown-item" href="#" data-toggle="modal" data-target="#myModal">${correo}</a>
                      <div class="dropdown-divider"></div>
                      <div class="dropdown-divider"></div>                       
                  </div>
              </ul> 
  </div>
        </nav>
        <div class="container mt-2">
            <div class="row">
                <c:forEach var="p" items="${productos}">
                    <div class="col-sm-4">
                        <div class="card">
                            <div class="card-header">
                                <label>${p.getNombres()}</label>
                            </div>
                            <div class="card-body">
                                <i>$.${p.getPrecio()}</i>
                                <img src="ControladorIMG?id=${p.getId()}" width="200" height="180">
                            </div>    
                            <div class="card-footer" text-center>
                                <label>${p.getDescripcion()}</label>
                                <div>
                                    <a href="Controlador?accion=AgregarCarrito&id=${p.getId()}" class="btn btn-outline-info">Agregar Carrito</a>
                                    <a href="Controlador?accion=Comprar&id=${p.getId()}"class="btn btn-danger">Comprar</a>
                                </div>
                            </div>    
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
                      <div class="modal fade" id="myModal" tabindex="-1" role="dialog">
                          <div class="modal-dialog modal-dialog-centered" role="document">
                              <div class="container col-lg-9">                   
                                  <div class="modal-content">                   
                                      <div class="pr-2 pt-1">                         
                                          <button type="button" class="close" data-dismiss="modal">
                                              <span aria-hidden="true">&times;</span>
                                          </button>                    
                                      </div>
                                      <div class="text-center">                         
                                          <img src="img/user.png" width="100" height="100">                         
                                      </div>
                                      <div class="modal-header text-center">                      
                                          <ul class="nav nav-pills">                           
                                              <li class="nav-item">
                                                  <a class="nav-link active" data-toggle="pill" href="#pills-iniciar">Iniciar Sesion</a>
                                              </li>
                                              <li class="nav-item">
                                                  <a class="nav-link" data-toggle="pill" href="#pills-registrar">Registrarse</a>
                                              </li>                          
                                          </ul>  
                                      </div>
                                      <div class="modal-body"> 
                                          <div class="tab-content" id="pills-tabContent">
                                              <!-- Iniciar Session -->
                                              <div class="tab-pane fade show active" id="pills-iniciar" role="tabpanel">
                                                  <form action="Controlador">
                                                      <div class="form-group">
                                                          <label>Email address</label>
                                                          <input type="email" name="txtemail" class="form-control" placeholder="email@example.com">
                                                      </div>
                                                      <div class="form-group">
                                                          <label>Password</label>
                                                          <input type="password" name="txtpass" class="form-control" placeholder="Password">
                                                      </div>                                   
                                                      <button type="submit" name="accion" value="Validar" class="btn btn-outline-danger btn-block">Iniciar</button>
                                                  </form>
                                              </div>
                                              <!-- Registrarse -->
                                              <div class="tab-pane fade" id="pills-registrar" role="tabpanel">
                                                  <form action="Controlador">                               
                                                      <div class="form-group">
                                                          <label>Nombres</label>
                                                          <input type="text" name="txtnom" class="form-control" placeholder="">
                                                      </div>
                                                      <div class="form-group">
                                                          <label>Dni</label>
                                                          <input type="text" maxlength="8" name="txtdni" class="form-control" placeholder="">
                                                      </div>
                                                      <div class="form-group">
                                                          <label>Direccion</label>
                                                          <input type="text" name="txtdire" class="form-control" placeholder="">
                                                      </div>
                                                      <div class="form-group">
                                                          <label>Email address</label>
                                                          <input type="email" name="txtemail" class="form-control" placeholder="email@example.com">
                                                      </div>
                                                      <div class="form-group">
                                                          <label>Password</label>
                                                          <input type="password" name="txtpass" class="form-control" placeholder="Password">
                                                      </div>                                  
                                                      <button type="submit" name="accion" value="Registrar" class="btn btn-outline-danger btn-block">Crear Cuenta</button>
                                                  </form>
                                              </div>                          
                                          </div> 
                                      </div>
                                  </div>
                              </div>
                          </div>
                      </div>
        
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    </body>
</html>
