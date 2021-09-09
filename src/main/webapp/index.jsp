<%-- 
    Document   : index1.jsp
    Created on : 30/08/2021, 09:58:46 AM
    Author     : camil
--%>

<%@page import="com.modelo.ValidadorURL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Inicio</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale-1, maximum-scale-1">
        <link href="styles.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <input type="hidden" name="parametro" id="parametro" value="<%=request.getParameter("url")%>"/> 
        <section id="slideLeft">
            <div onclick="previousSlide()">
                <img src="arrowLeft.png" width="50" height="120" alt="mover a la izquierda"/>
            </div>
        </section>
        <section id="slideRight" >
            <div onclick="nextSlide()">
                <img src="arrowRight.png" width="50" height="120" alt="mover a la derecha"/>
            </div>
        </section>
        
        
        <section id="informacionLink1" style="" name="slideInfo" class="slideInfo">
            <section id="urlSitio">
                <h3 id="dirURL">SITIO 1</h3>                
            </section>
            <section id="sec-menu">
                <ul class="menu">
                    <li><a id="tamaño" href="#tamaño" onclick="seccion(1)" class="conf-esp ">Tamaño en <br>KBytes</a></li>
                    <li><a id="lineas" href="#lineas" onclick="seccion(2)">Líneas</a></li>
                    <li><a id="ocurrencia" href="#ocurrencia" onclick="seccion(3)" class="conf-esp">Ocurrencia de <br>palabras</a></li>
                    <li><a id="enlaces" href="#enlaces" onclick="seccion(4)">Enlaces</a></li>
                    <li><a id="descargar" href="#descargar" onclick="seccion(5)">Descargar imágenes</a></li>
                    <li><a id="sitioos" href="#sitioos" onclick="seccion(6)">Sitios</a></li>
                    <li><a id="archivos" href="#archivos" onclick="seccion(7)">Archivos</a></li>
                </ul>
            </section>
            <section id="contenido" name="contenido">
                <div id="contenid" class="scc">
                    <h2>Tamaño en Kbytes</h2>
                    <div id="tamkb">
                        <h2 id="rTamañokb"></h2>
                    </div>
                </div>

                <div class="scc">
                    <h2>Lineas</h2>
                    <section id="cont-linea">
                        Cantidad 
                        <div id="div-line"><input type="text" class="inStyle" id="cant" value="XXXXXXXXX" readonly></label></div><br>
                        Tamaño 
                        <div id="div-line"><input type="text" class="inStyle" id="tam" value="XXXXXXXXX" readonly></label></div>
                    </section>
                </div>

                <div class="scc">
                    <h2>Ocurrencia de palabras</h2>
                    <section id="cont-ocurrencia">
                        <table class="table" id="tableOcurrencia">
                            <thead class="table-light">
                                <tr>
                                    <th>Palabra</th>
                                    <th>Cantidad</th>	
                                </tr>
                            </thead>
                            <tbody id="bodyTablaOcurrencia">
                                <tr>
                                    <td>Celda 1</td>
                                    <td>Celda 2</td>
                                </tr>

                            </tbody>
                        </table>
                    </section>
                </div>

                <div class="scc">
                    <h2>Enlaces</h2>
                    <section id="cont-desImg">
                        <table class="table">
                            <thead class="table-light">
                                <tr>
                                    <th>Enlaces</th>
                                </tr>
                            </thead>
                            <tbody id="bodyTabla">
                                <tr class="divisor">
                                    <td rowspan="4">España</td>
                                </tr>
                                <tr>
                                    <td>Cantidad</td>
                                    <td>10</td>		
                                </tr>
                                <tr>
                                    <td>Cantidad</td>
                                    <td>10</td>
                                </tr>
                                <tr>
                                    <td>Cantidad</td>
                                    <td >10</td>
                                </tr>
                                <tr class="divisor">
                                    <td rowspan="5">España</td>
                                </tr>
                                <tr>
                                    <td>Cantidad</td>
                                    <td>10</td>
                                </tr>
                                <tr>
                                    <td>Cantidad</td>
                                    <td>10</td>
                                </tr>
                                <tr>
                                    <td>Cantidad</td>
                                    <td >10</td>
                                </tr>
                                <tr>
                                    <td>Cantidad</td>
                                    <td >10</td>
                                </tr>
                                <tr class="divisor">
                                    <td rowspan="5">España</td>
                                </tr>
                                <tr>
                                    <td>Cantidad</td>
                                    <td>10</td>
                                </tr>
                                <tr>
                                    <td>Cantidad</td>
                                    <td>10</td>
                                </tr>
                                <tr>
                                    <td>Cantidad</td>
                                    <td >10</td>
                                </tr>
                                <tr>
                                    <td>Cantidad</td>
                                    <td >10</td>
                                </tr>
                            </tbody>
                        </table>
                    </section>
                </div>

                <div class="scc">
                    <h2>Descargar Im&aacute;genes</h2>
                    <section id="cont-desImg">
                        <table class="table">
                            <thead class="table-light">
                                <tr>
                                    <th>Imágenes</th>
                                </tr>
                            </thead>
                            <tbody id="bodyTabla">
                                <tr>
                                    <td>Celda 1</td>
                                </tr>
                                <tr>
                                    <td>Celda 3</td>
                                </tr>
                                <tr>
                                    <td>Celda 5</td>
                                </tr>
                                <tr>
                                    <td>Celda 1</td>
                                </tr>
                                <tr>
                                    <td>Celda 3</td>
                                </tr>
                                <tr>
                                    <td>Celda 5</td>
                                </tr>
                                <tr>
                                    <td>Celda 1</td>
                                </tr>
                                <tr>
                                    <td>Celda 3</td>
                                </tr>
                                <tr>
                                    <td>Celda 5</td>
                                </tr>
                            </tbody>
                        </table>
                    </section>
                </div>

                <div class="scc">
                    <h2>Sitios</h2>
                    <section id="cont-sitios">
                        <br><br><br><br><label>Requiere logueo/ No requiere logueo</label><br><br><br><br><br><br>
                        <label>Tiene sitios con captura de datos / No<br>
                            Tiene sitios con captura de datos</label>
                    </section>
                </div>

                <div class="scc">
                    <h2>Archivos</h2>
                    <section id="cont-archivos">
                        <br><br>
                        <ul>
                            <li>XXXXXXXXXXXXXXXX</li>
                            <li>XXXXXXXXXXXXXXXXxx</li>
                            <li>XXXXXXXXXXXXXXXXx</li>
                            <li>XXXXXXXXXXXXXXXXxxxx</li>
                            <li>XXXXXXXXXXXXXxxx</li>
                            <li>XXXXXXXXXXXXXxxxxx</li>
                        </ul>
                    </section>
                </div>

            </section>

        </section>


    </section>

    <script type="text/javascript" src="eventos.js"></script>
</body>
</html>
