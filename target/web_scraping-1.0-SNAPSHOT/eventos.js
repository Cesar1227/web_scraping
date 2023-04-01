var scc = document.getElementsByClassName('scc');
var nURLs = 1;
var moveSlides = true;
var slides = document.getElementsByClassName('slideInfo');
var slideToShow = 0;


function validar() {
    var x = document.getElementById("sec-menu");
    var x2 = document.getElementById("contenido");
    if (x.style.display === "block" && x2.style.display === "block") {
        console.log("deshabilitar");
    } else {
        console.log("este es");
        x.style.display = "block";
        x2.style.display = "block";
        seccion(0);
    }
    /*  var jh=document.getElementById("url").value;
     console.log(jh);
     var jh2=document.getElementById("analizar").value;
     console.log(jh2);*/

}




function agregarURL() {
    if (nURLs < 4) {
        nURLs++;
        campoDiv = document.getElementById('inLinks');
        var elemento = document.createElement("input");

        elemento.id = "url" + nURLs;
        elemento.setAttribute("type", "text");
        elemento.setAttribute("name", "url");
        elemento.classList.add("url");
        elemento.setAttribute("placeholder", "Ingresar URL...");
        campoDiv.appendChild(elemento);
        clonarSeccion("informacionLink" + nURLs);
        slides = document.getElementsByClassName('slideInfo');
    }

}

function quitarURL() {
    if (nURLs > 1) {
        var contenedor = document.getElementById("inLinks");
        var id = "url" + nURLs;
        console.log("a borrar: ", id);
        var ultimo = document.getElementById(id);

        console.log("f1 hijo de: ", ultimo);
        contenedor.removeChild(ultimo);
        var seccion = document.getElementById("informacionLink" + nURLs);

        seccion.parentNode.removeChild(seccion);
        nURLs--;
        slides = document.getElementsByClassName('slideInfo');
    }
    //ultimo.parentNode
}

function clonarSeccion(ide) {
    var original = document.getElementById("informacionLink1");
    var nuevo = original.cloneNode(true);
    nuevo.id = ide;

    nuevo.style.display = "none";

    destino = document.getElementById("contenedor");
    destino.appendChild(nuevo);
}

function slide(index) {
    slides[slideToShow].style.display = "none";
    slides[index].style.display = "block";
    slideToShow = index;
    scc = slides[index].getElementsByClassName('scc');
}

function previousSlide() {
    if (moveSlides) {
        if (slideToShow > 0) {
            slide(slideToShow - 1);

        }
    }
}

function nextSlide() {
    if (moveSlides) {
        console.log("cant slides " + slides.length)
        if (slideToShow < slides.length - 1) {
            slide(slideToShow + 1);

        }
    }
}

function mostrar() {

    var ele1 = document.getElementById("informacionLink1");
    var men = document.getElementById("sec-menu");
    var cont = document.getElementById("contenido");
    men.style.display = "block";
    cont.style.display = "block";
    seccion(1);
}

function seccion(num) {

    if (num == 0) {
        console.log("definido");
        for (i = 0; i < scc.length; i++)
        {
            scc[i].classList.add("close");

        }
    } else {


        for (i = 0; i < scc.length; i++)
        {
            scc[i].classList.remove("sky");
            if (num == 1) {
                scc[i].classList.add("close");
                scc[0].classList.remove("close");
                scc[0].classList.add("sky");
            }
            if (num == 2) {
                scc[i].classList.add("close");
                scc[1].classList.remove("close");
                scc[1].classList.add("sky");
            }
            if (num == 3) {
                scc[i].classList.add("close");
                scc[2].classList.remove("close");
                scc[2].classList.add("sky");
            }
            if (num == 4) {
                scc[i].classList.add("close");
                scc[3].classList.remove("close");
                scc[3].classList.add("sky");
            }
            if (num == 5) {
                scc[i].classList.add("close");
                scc[4].classList.remove("close");
                scc[4].classList.add("sky");
            }
            if (num == 6) {
                scc[i].classList.add("close");
                scc[5].classList.remove("close");
                scc[5].classList.add("sky");
            }
            if (num == 7) {
                scc[i].classList.add("close");
                scc[6].classList.remove("close");
                scc[6].classList.add("sky");
            }
        }
    }
}



function obtenerDatos() {

    var xhr = new XMLHttpRequest();
    let dirUrl = document.getElementById("parametro").value;
    console.log("URL:" + dirUrl);

    dirUrl = dirUrl.split("\n");
    let i = 0;
    while (i < dirUrl.length - 1) {
        clonarSeccion(i + 2);
        i++;
    }
    console.log(dirUrl.length);
    var url = "ValiURL?url=";
    for (let i = 0; i < dirUrl.length; i++) {
        if (i < 1) {
            url = url + dirUrl[i];
        } else {
            url = url + "%20" + dirUrl[i];
        }


    }
    console.log(url);
    xhr.open("GET", url, true);
    xhr.onload = function (e) {
        if (xhr.readyState = 4) {
            if (xhr.status = 200) {
                showScraping(xhr);
            } else {
                console.error(xhr.statusText);
            }
        }
    };
    xhr.onerror - function (e) {
        console.error(xhr.statusText);
    };
    xhr.send(null);
}

function showScraping(xhr) {
    slides = document.getElementsByClassName('slideInfo');
    //var links=json.links;
    var elementsScc;
    let cant = slides.length;
    let i = 0
    var JSON = eval(xhr.responseText);
    console.log(JSON);

    var cUrl = document.getElementsByClassName("rScrap rUrl");
    var cTamaño = document.getElementsByClassName("rScrap rTamaño");
    var cLineas = document.getElementsByClassName("rScrap rLineas");
    var cOcurrencia = document.getElementsByClassName("rScrap rOcurrencia");
    var cEnlaces = document.getElementsByClassName("rScrap rEnlaces");
    var cLinkImagenes = document.getElementsByClassName("rScrap rLinkImagenes");
    var cLogueo = document.getElementsByClassName("rScrap rLogueo");
    var cCapDatos = document.getElementsByClassName("rScrap rCapDatos");
    var cArchivos = document.getElementsByClassName("rScrap rArchivos");

    /*for(let iter : JSON){
     
     }*/
    for (let i = 0; i < cant; i++) {
        cUrl[i].innerHTML = JSON[i].tourl;
        cTamaño[i].innerHTML = JSON[i].tamanopage;
        for (let j = 0; j < JSON[i].tamlineas.length; j++) {
            let tr = document.createElement("tr");
            let td1 = document.createElement("td");
            let td2 = document.createElement("td");
            td1.innerHTML = j + 1;
            td2.innerHTML = JSON[i].tamlineas[j];
            tr.appendChild(td1);
            tr.appendChild(td2);
            cLineas[i].childNodes[3].appendChild(tr);
        }

        //OCURRENCIA DE PALABRAS
        for (let j = 0; j < JSON[i].palabras.length; j++) {
            let tr = document.createElement("tr");
            let td1 = document.createElement("td");
            let td2 = document.createElement("td");
            td1.innerHTML = JSON[i].palabras[j];
            td2.innerHTML = JSON[i].ocupalabras[j];
            tr.appendChild(td1);
            tr.appendChild(td2);
            cOcurrencia[i].childNodes[3].appendChild(tr);
        }

        //ENLACES
        var pdf = [];
        var pdfO = [];
        var html = [];
        var htmlO = [];
        var otros = [];
        var otrosO = [];

        for (let j = 0; j < JSON[i].numenlc; j++) {
            if (JSON[i].tipo_enlc[j] == "#1") {
                pdf.push(JSON[i].enlcpages[j]);
                if (JSON[i].enlcbrokens[j]) {
                    pdfO.push("ACTIVO");
                } else {
                    pdfO.push("ROTO");
                }
            } else if (JSON[i].tipo_enlc[j] == "#2") {
                html.push(JSON[i].enlcpages[j]);
                if (JSON[i].enlcbrokens[j]) {
                    htmlO.push("ACTIVO");
                } else {
                    htmlO.push("ROTO");
                }
            } else {
                otros.push(JSON[i].enlcpages[j]);
                if (JSON[i].enlcbrokens[j]) {
                    otrosO.push("ACTIVO");
                } else {
                    htmlO.push("ROTO");
                }

            }
        }

        var div = [];
        var etiq = ["PDF", "HTML", "OTRO"];
        var anchos = [pdf.length, html.length, otros.length];
        var td;
        let k = 0;
        while (k < 3) {
            div[k] = document.createElement("tr");
            td = document.createElement("td");
            td.setAttribute("rowspan", anchos[k] + 1);
            td.innerHTML = etiq[k];
            div[k].classList.add("divisor");
            div[k].appendChild(td);
            /*cEnlaces[i].childNodes[3].appendChild(div[k]);*/
            k++;
        }

        /*div[0].childNodes[0].setAttribute("rowspan",pdf.length);
         div[1].childNodes[0].setAttribute("rowspan",html.length);
         div[2].childNodes[0].setAttribute("rowspan",otros.length);*/

        //INGRESANDO ENLACES A PDF
        if (pdf.length > 0) {
            cEnlaces[i].childNodes[3].appendChild(div[0]);
        }
        for (let j = 0; j < pdf.length; j++) {

            let tr = document.createElement("tr");
            let td1 = document.createElement("td");
            let td2 = document.createElement("td");

            td1.innerHTML = pdf[j];
            td1.style.width="800px";
            td2.innerHTML = pdfO[j];
            tr.appendChild(td1);
            tr.appendChild(td2);
            cEnlaces[i].childNodes[3].appendChild(tr);
        }

        //INGRESANDO ENLACES A HTML
        if (html.length > 0) {
            cEnlaces[i].childNodes[3].appendChild(div[1]);
        }
        for (let j = 0; j < html.length; j++) {
            let tr = document.createElement("tr");
            let td1 = document.createElement("td");
            let td2 = document.createElement("td");

            td1.innerHTML = html[j];
            td1.style.width="800px";
            td2.innerHTML = htmlO[j];
            tr.appendChild(td1);
            tr.appendChild(td2);
            cEnlaces[i].childNodes[3].appendChild(tr);
        }

        //INGRESANDO ENLACES A OTROS
        if (otros.length > 0) {
            cEnlaces[i].childNodes[3].appendChild(div[2]);
        }
        for (let j = 0; j < otros.length; j++) {

            let tr = document.createElement("tr");
            let td1 = document.createElement("td");
            let td2 = document.createElement("td");

            td1.innerHTML = otros[j];
            td1.style.width="800px";
            td2.innerHTML = otrosO[j];
            tr.appendChild(td1);
            tr.appendChild(td2);
            cEnlaces[i].childNodes[3].appendChild(tr);
        }
        
        var etiqlabel=document.createElement("label");
        etiqlabel.style.align="center";
        etiqlabel.innerHTML=JSON[i].numenlc;
        cEnlaces[i].parentNode.appendChild(etiqlabel);


        //INGRESANDO IMAGENES
        var etiA;
        for (let j = 0; j < JSON[i].enlcimg.length; j++) {
            let tr = document.createElement("tr");
            let td = document.createElement("td");
            
            etiA = document.createElement("a");
            etiA.setAttribute("href", JSON[i].enlcimg[j]);
            etiA.setAttribute("target", "_blank");
            etiA.innerHTML = JSON[i].enlcimg[j];
            td.appendChild(etiA);
            tr.appendChild(td);
            cLinkImagenes[i].childNodes[3].appendChild(tr);
            
            
        }

        //CAPTURA DE DATOS Y LOGUEO
        cLogueo[i].innerHTML = (JSON[i].contlog ? "LA PAGINA CONTIENE LOGUEO" : "LA PAGINA NO CONTIENE LOGUEO");
        cCapDatos[i].innerHTML = (JSON[i].contforms ? "LA PAGINA CONTIENE CAPTURA DE DATOS" : "LA PAGINA NO CONTIENE CAPTURA DE DATOS");

        //ARCHIVOS
        var etiLi;
        var etiUl = document.createElement("ul");
        for (let j = 0; j < JSON[i].listaarch.length; j++) {
            etiLi = document.createElement("li");
            etiLi.innerHTML = JSON[i].listaarch[j];
            etiUl.appendChild(etiLi);
        }
        cArchivos[i].appendChild(etiUl);

    }
}

window.onload = function () {
    obtenerDatos();
    seccion(0);
};


