var scc = document.getElementsByClassName('scc');
var nURLs = 1;
var moveSlides=true;
var slides = document.getElementsByClassName('slideInfo');
var slideToShow=0;

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
    nURLs++;
    campoDiv = document.getElementById('inLinks');
    var elemento = document.createElement("input");

    elemento.id = "url" + nURLs;
    elemento.setAttribute("type", "text");
    elemento.setAttribute("name", "url");
    elemento.classList.add("url");
    elemento.setAttribute("placeholder", "Ingresar URL...");
    campoDiv.appendChild(elemento);
    clonarSeccion("informacionLink"+nURLs);
    slides = document.getElementsByClassName('slideInfo');
}

function quitarURL() {
    if (nURLs > 1) {
        var contenedor = document.getElementById("inLinks");
        var id = "url" + nURLs;
        console.log("a borrar: ", id);
        var ultimo = document.getElementById(id);

        console.log("f1 hijo de: ", ultimo);
        contenedor.removeChild(ultimo);
        var seccion=document.getElementById("informacionLink"+nURLs);
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
    nuevo.style.display="none";
    destino = document.getElementById("contenedor");
    destino.appendChild(nuevo);
}

function slide(index){
    slides[slideToShow].style.display="none";
    slides[index].style.display="block";
    slideToShow=index;
    scc = slides[index].getElementsByClassName('scc');
}

function previousSlide(){
    if(moveSlides){
        if(slideToShow>0){
            slide(slideToShow-1);            
        }
    }
}

function nextSlide(){
    if(moveSlides){
        console.log("cant slides "+slides.length)
        if(slideToShow<slides.length-1){
            slide(slideToShow+1);            
        }
    }
}

function mostrar(){
    var ele1=document.getElementById("informacionLink1");
    var men=document.getElementById("sec-menu");
    var cont=document.getElementById("contenido");
    men.style.display="block";
    cont.style.display="block";
    seccion(1);
}

function seccion(num){
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

