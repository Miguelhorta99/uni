/* global tipo */

function validationFunction(validation) {
    if (validation === "") {
        return false;
    }
}


function removeAllChildNodes(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}

function getAnuncios() {

    var actionUrl = "http://alunos.di.uevora.pt/tweb/t1/roomsearch";

    $.post(actionUrl, {tipo: "oferta"}, function (tipo) {

        for (let a = 0; a < 3; a++) {

            $("#anuncios").show();

            appearanceOfAnuncios(tipo.resultados[a].anunciante,
                    tipo.resultados[a].aid, tipo.resultados[a].zona, tipo.resultados[a].data, tipo.resultados[a].estado, "anuncios");

        }


    });

    $.post(actionUrl, {tipo: "procura"}, function (tipo) {

        for (let a = 0; a < 3; a++) {

            $("#anuncios").show();

            appearanceOfAnuncios(tipo.resultados[a].anunciante,
                    tipo.resultados[a].aid, tipo.resultados[a].zona, tipo.resultados[a].data, tipo.resultados[a].estado, "anuncios");

        }


    });
}

function getDetalhesAnuncios(aidAnuncio) {

    var actionUrl = "http://alunos.di.uevora.pt/tweb/t1/anuncio";

    $.post(actionUrl, {aid: aidAnuncio}, function (aid) {

        if (aid.resultado === "ok") {

            $("#anuncios").hide();

            $("#detalhesAnuncio").show();

            appearanceOfAnunciosDetalhes(aid.anuncio.contacto, aid.anuncio.anunciante, aid.anuncio.detalhes,
                    aid.anuncio.aid, aid.anuncio.zona, aid.anuncio.tipo_alojamento, aid.anuncio.data, aid.anuncio.preco,
                    aid.anuncio.genero, aid.anuncio.estado, "detalhesAnuncio");

        }


    });
}





function appearanceOfAnuncios(anuncianteAnuncio, aidAnuncio, zonaAnuncio, dataAnuncio, estadoAnuncio, qualDiv) {

    pNew = document.createElement("p");
    pNew.setAttribute("class", "AnunciosparagrafoAid");
    pNew.appendChild(document.createTextNode("Aid: " + aidAnuncio));
    document.getElementById(qualDiv).appendChild(pNew);

    pNew = document.createElement("p");
    pNew.setAttribute("class", "AnunciosParagrafoAnunciante");
    pNew.appendChild(document.createTextNode("Anunciante: " + anuncianteAnuncio));
    document.getElementById(qualDiv).appendChild(pNew);

    pNew = document.createElement("p");
    pNew.setAttribute("class", "AnunciosparagrafoZona");
    pNew.appendChild(document.createTextNode("Zona: " + zonaAnuncio));
    document.getElementById(qualDiv).appendChild(pNew);

    pNew = document.createElement("p");
    pNew.setAttribute("class", "AnunciosparagrafoData");
    pNew.appendChild(document.createTextNode("Data: " + dataAnuncio));
    document.getElementById(qualDiv).appendChild(pNew);

    pNew = document.createElement("p");
    pNew.setAttribute("class", "AnunciosparagrafoEstado");
    pNew.appendChild(document.createTextNode("Estado: " + estadoAnuncio));
    document.getElementById(qualDiv).appendChild(pNew);

    var bNew = document.createElement("button");
    bNew.setAttribute("id", "detalhesAnunciobutton");
    bNew.onclick = function () {
        getDetalhesAnuncios(aidAnuncio);
    };
    bNew.setAttribute("class", "buttonsOfAnuncios");
    bNew.appendChild(document.createTextNode("Detalhes do Anuncio"));
    document.getElementById(qualDiv).appendChild(bNew);

    pNew = document.createElement("br");
    document.getElementById(qualDiv).appendChild(pNew);

    pNew = document.createElement("hr");
    pNew.setAttribute("class", "separateLine");
    document.getElementById(qualDiv).appendChild(pNew);


}

function appearanceOfAnunciosDetalhes(contactoAnuncio, anuncianteAnuncio, detalhesAnuncio, aidAnuncio, zonaAnuncio, tipoAlojamentoAnuncio, dataAnuncio, precoAnuncio, generoAnuncio, estadoAnuncio, qualDiv) {

    var pNew = document.createElement("p");
    pNew.setAttribute("class", "AnunciosParagrafoContacto");
    pNew.appendChild(document.createTextNode("Contacto: " + contactoAnuncio));
    document.getElementById(qualDiv).appendChild(pNew);

    pNew = document.createElement("p");
    pNew.setAttribute("class", "AnunciosParagrafoAnunciante");
    pNew.appendChild(document.createTextNode("Anunciante: " + anuncianteAnuncio));
    document.getElementById(qualDiv).appendChild(pNew);

    pNew = document.createElement("p");
    pNew.setAttribute("class", "AnunciosparagrafoDetalhes");
    pNew.appendChild(document.createTextNode("Detalhes do anuncio: " + detalhesAnuncio));
    document.getElementById(qualDiv).appendChild(pNew);

    pNew = document.createElement("p");
    pNew.setAttribute("class", "AnunciosparagrafoAid");
    pNew.appendChild(document.createTextNode("Aid: " + aidAnuncio));
    document.getElementById(qualDiv).appendChild(pNew);

    pNew = document.createElement("p");
    pNew.setAttribute("class", "AnunciosparagrafoZona");
    pNew.appendChild(document.createTextNode("Zona: " + zonaAnuncio));
    document.getElementById(qualDiv).appendChild(pNew);

    pNew = document.createElement("p");
    pNew.setAttribute("class", "AnunciosparagrafoTipoAlojamento");
    pNew.appendChild(document.createTextNode("Tipo: " + tipoAlojamentoAnuncio));
    document.getElementById(qualDiv).appendChild(pNew);

    pNew = document.createElement("p");
    pNew.setAttribute("class", "AnunciosparagrafoData");
    pNew.appendChild(document.createTextNode("Data: " + dataAnuncio));
    document.getElementById(qualDiv).appendChild(pNew);

    pNew = document.createElement("p");
    pNew.setAttribute("class", "AnunciosparagrafoPreco");
    pNew.appendChild(document.createTextNode("Preço: " + precoAnuncio));
    document.getElementById(qualDiv).appendChild(pNew);

    pNew = document.createElement("p");
    pNew.setAttribute("class", "AnunciosparagrafoGenero");
    pNew.appendChild(document.createTextNode("Genero: " + generoAnuncio));
    document.getElementById(qualDiv).appendChild(pNew);

    pNew = document.createElement("p");
    pNew.setAttribute("class", "AnunciosparagrafoEstado");
    pNew.appendChild(document.createTextNode("Estado: " + estadoAnuncio));
    document.getElementById(qualDiv).appendChild(pNew);

    var bNew = document.createElement("button");
    bNew.setAttribute("id", "mensagemAnunciobutton");
    bNew.onclick = function () {
        $("#enviarMensagemAoAnunciante").show();
        $("#detalhesAnuncio").hide();
    };
    bNew.setAttribute("class", "buttonsOfMensagemAnuncios");
    bNew.appendChild(document.createTextNode("Enviar mensagem ao anunciante"));
    document.getElementById(qualDiv).appendChild(bNew);

    pNew = document.createElement("br");
    document.getElementById(qualDiv).appendChild(pNew);


}


$(document).ready(function () {

    getAnuncios();

    $("#acessoReservado").hide();
    $("#consultarAnuncioDiv").hide();
    $("#registarOferta").hide();
    $("#registarProcura").hide();
    $("#detalhesAnuncio").hide();
    $("#procurarAnuncio").hide();
    $("#consultarMensagensUtilizadorRegistado").hide();
    $("#enviarMensagemAoAnunciante").hide();

    $("#anuncios_button").click(function () {
        $("#consultarAnuncioDiv").hide();
        $("#acessoReservado").hide();
        $("#registarOferta").hide();
        $("#registarProcura").hide();
        $("#detalhesAnuncio").hide();
        $("#procurarAnuncio").hide();
        $("#consultarMensagensUtilizadorRegistado").hide();
        $("#enviarMensagemAoAnunciante").hide();

        $("#anuncios").show();
    });

    $("#procurar_anuncios_button").click(function () {
        $("#consultarAnuncioDiv").hide();
        $("#acessoReservado").hide();
        $("#registarOferta").hide();
        $("#registarProcura").hide();
        $("#detalhesAnuncio").hide();
        $("#consultarMensagensUtilizadorRegistado").hide();
        $("#enviarMensagemAoAnunciante").hide();
        $("#anuncios").hide();

        $("#procurarAnuncio").show();
    });

    $("#acessoReservado_button").click(function () {
        $("#consultarAnuncioDiv").hide();
        $("#registarOferta").hide();
        $("#registarProcura").hide();
        $("#detalhesAnuncio").hide();
        $("#procurarAnuncio").hide();
        $("#consultarMensagensUtilizadorRegistado").hide();
        $("#enviarMensagemAoAnunciante").hide();
        $("#anuncios").hide();

        $("#acessoReservado").show();
    });

    $("#registar_oferta_button").click(function () {
        $("#consultarAnuncioDiv").hide();
        $("#acessoReservado").hide();
        $("#registarProcura").hide();
        $("#detalhesAnuncio").hide();
        $("#procurarAnuncio").hide();
        $("#consultarMensagensUtilizadorRegistado").hide();
        $("#enviarMensagemAoAnunciante").hide();
        $("#anuncios").hide();

        $("#registarOferta").show();
    });

    $("#registar_procura_button").click(function () {
        $("#consultarAnuncioDiv").hide();
        $("#registarOferta").hide();
        $("#acessoReservado").hide();
        $("#detalhesAnuncio").hide();
        $("#procurarAnuncio").hide();
        $("#consultarMensagensUtilizadorRegistado").hide();
        $("#enviarMensagemAoAnunciante").hide();
        $("#anuncios").hide();

        $("#registarProcura").show();
    });

    $("#utlizador_registado_button").click(function () {
        $("#consultarAnuncioDiv").hide();
        $("#registarOferta").hide();
        $("#registarProcura").hide();
        $("#detalhesAnuncio").hide();
        $("#procurarAnuncio").hide();
        $("#acessoReservado").hide();
        $("#enviarMensagemAoAnunciante").hide();
        $("#anuncios").hide();

        $("#consultarMensagensUtilizadorRegistado").show();
    });



    $("#registarOfertaForm").on("submit", function (aid) {

        var tipo_alojamento = document.forms.registarOfertaForm.tipo_alojamento.value;
        var nrQuartosCasa = document.forms.registarOfertaForm.nrQuartosCasa.value;
        var genero = document.forms.registarOfertaForm.genero.value;

        var preco = document.forms.registarOfertaForm.preco.value;
        var zona = document.forms.registarOfertaForm.zona.value;
        var equipado = document.forms.registarOfertaForm.equipado.value;

        var detalhes = document.forms.registarOfertaForm.detalhes.value;
        var anunciante = document.forms.registarOfertaForm.anunciante.value;
        var contacto = document.forms.registarOfertaForm.contacto.value;



        if (validationFunction(tipo_alojamento) == false || validationFunction(nrQuartosCasa) == false || validationFunction(genero) == false ||
                validationFunction(preco) == false || validationFunction(zona) == false || validationFunction(equipado) == false ||
                validationFunction(detalhes) == false || validationFunction(anunciante) == false || validationFunction(contacto) == false) {
            alert("The box(s) is(are) not filled");
        } else {
            event.preventDefault();

            var formValues = $(this).serialize();
            var actionUrl = "http://alunos.di.uevora.pt/tweb/t1/registaoferta";

            $.post(actionUrl, formValues, function (tipo) {

                if (tipo.resultado === "ok") {
                    document.getElementById("responseRegistarOferta").innerHTML = "Anuncio de oferta registada com sucesso.";
                } else {
                    document.getElementById("responseRegistarOferta").innerHTML = "Erro! Não foi possível registar o Anuncio de Oferta.";
                }
            });
        }
    });

    $("#registarProcuraForm").on("submit", function (aid) {

        var tipo_alojamento = document.forms.registarProcuraForm.tipo_alojamento.value;
        var nrQuartosCasa = document.forms.registarProcuraForm.nrQuartosCasa.value;
        var genero = document.forms.registarProcuraForm.genero.value;

        var preco = document.forms.registarProcuraForm.preco.value;
        var zona = document.forms.registarProcuraForm.zona.value;
        var equipado = document.forms.registarProcuraForm.equipado.value;

        var detalhes = document.forms.registarProcuraForm.detalhes.value;
        var anunciante = document.forms.registarProcuraForm.anunciante.value;
        var contacto = document.forms.registarProcuraForm.contacto.value;



        if (validationFunction(tipo_alojamento) == false || validationFunction(nrQuartosCasa) == false || validationFunction(genero) == false ||
                validationFunction(preco) == false || validationFunction(zona) == false || validationFunction(equipado) == false ||
                validationFunction(detalhes) == false || validationFunction(anunciante) == false || validationFunction(contacto) == false) {
            alert("The box(s) is(are) not filled");
        } else {
            event.preventDefault();

            var formValues = $(this).serialize();
            var actionUrl = "http://alunos.di.uevora.pt/tweb/t1/registaprocura";

            $.post(actionUrl, formValues, function (tipo) {

                if (tipo.resultado === "ok") {
                    document.getElementById("responseRegistarProcura").innerHTML = "Anuncio de procura registada com sucesso.";
                } else {
                    document.getElementById("responseRegistarProcura").innerHTML = "Erro! Não foi possível registar o Anuncio de Procura.";
                }
            });
        }
    });


    $("#enviarMensagemAoAnuncianteForm").on("submit", function (aid) {

        var aid = document.forms.enviarMensagemAoAnuncianteForm.aid.value;
        var remetente = document.forms.enviarMensagemAoAnuncianteForm.remetente.value;
        var msg = document.forms.enviarMensagemAoAnuncianteForm.msg.value;


        if (validationFunction(aid) == false || validationFunction(remetente) == false || validationFunction(msg) == false) {
            alert("The box(s) is(are) not filled");
        } else {
            event.preventDefault();

            var formValues = $(this).serialize();
            var actionUrl = "http://alunos.di.uevora.pt/tweb/t1/contactar";

            $.post(actionUrl, formValues, function (tipo) {

                if (tipo.resultado === "ok") {
                    document.getElementById("responseEnviarMensagemAoAnunciante").innerHTML = "Mensagem enviada com sucesso ao anunciante.";
                } else {
                    document.getElementById("responseEnviarMensagemAoAnunciante").innerHTML = "Erro! Não foi possível enviar a mensagem ao anunciante.";
                }
            });
        }
    });


    $("#procurarAnuncioForm").on("submit", function (aid) {

        $(".procurarAnuncioForm").remove(); //remove os elementos que foram adicionados resultantes da pesquisa anterior

        var container = document.getElementById("anuncios");
        removeAllChildNodes(container);

        var tipoValue = document.forms.procurarAnuncioForm.tipo.value;
        var zonaValue = document.forms.procurarAnuncioForm.zona.value;
        var nomeAnuncianteValue = document.forms.procurarAnuncioForm.nomeAnunciante.value;

        if (validationFunction(tipoValue) == false) {
            alert("É obrigatorio preencher o campo (tipo)!");
        } else {
            aid.preventDefault();

            var formValues = $(this).serialize();
            var actionUrl = "http://alunos.di.uevora.pt/tweb/t1/roomsearch";

            $.post(actionUrl, formValues, function (tipo) {

                if (tipo.resultado === "ok") {


                    for (let a in tipo.resultados) {

                        $("#procurarAnuncio").hide();
                        $("#anuncios").show();

                        appearanceOfAnuncios(tipo.resultados[a].anunciante,
                                tipo.resultados[a].aid, tipo.resultados[a].zona, tipo.resultados[a].data, tipo.resultados[a].estado, "anuncios");

                    }

                }
            });
        }
    });


    $("#consultarUmNovoAnuncioForm").on("submit", function (aid) {

        aid.preventDefault();

        var actionUrl = "http://alunos.di.uevora.pt/tweb/t1/gereanuncios";

        $.post(actionUrl, function (tipo) {

            if (tipo.resultado == "ok") {

                var container = document.getElementById("consultarAnuncioDiv");
                removeAllChildNodes(container);

                var titleConsultarAnuncio = document.createElement("p");
                titleConsultarAnuncio.setAttribute("class", "AnuncioParagrafoNome");
                titleConsultarAnuncio.innerHTML = "Lista dos dados do anuncio: ";
                document.getElementById("consultarAnuncioDiv").appendChild(titleConsultarAnuncio);

                var table = document.createElement("table");
                var thead = document.createElement("thead");
                var tbody = document.createElement("tbody");

                table.appendChild(thead);
                table.appendChild(tbody);

                document.getElementById("consultarAnuncioDiv").appendChild(table);

                var linha1 = document.createElement("tr");
                var ativo = document.createElement("th");
                ativo.innerHTML = "Anuncio ativo";
                var inativo = document.createElement("th");
                inativo.innerHTML = "Anuncio inativo";

                linha1.appendChild(ativo);
                linha1.appendChild(inativo);

                thead.appendChild(linha1);

                for (let a in tipo.ativo) {

                    var novalinha = document.createElement("tr");

                    var ativoRecebido = document.createElement("td");
                    ativoRecebido.innerHTML = tipo.ativo[a];
                    console.log(tipo.ativo[a]);
                    console.log(tipo.inativo[a]);
                    var inativoRecebido = document.createElement("td");
                    inativoRecebido.innerHTML = tipo.inativo[a];

                    novalinha.appendChild(ativoRecebido);
                    novalinha.appendChild(inativoRecebido);

                    tbody.appendChild(novalinha);
                }


                $("#consultarAnuncioDiv").show();
                $("#acessoReservado").hide();
            } else {
                alert("Erro! Algo correu mal");
            }
        });

    });



    $("#validarUmNovoAnuncioForm").on("submit", function (aid) {

        var aid = document.forms.validarUmNovoAnuncioForm.aid.value;
        var estado = document.forms.validarUmNovoAnuncioForm.estado.value;
        var descricao = document.forms.validarUmNovoAnuncioForm.descricao.value;

        if (validationFunction(aid) == false || validationFunction(estado) == false || validationFunction(descricao) == false) {
            alert("The box(s) is(are) not filled");
        } else {
            event.preventDefault();

            var formValues = $(this).serialize();
            var actionUrl = "http://alunos.di.uevora.pt/tweb/t1/controloanuncio";

            $.post(actionUrl, formValues, function (tipo) {

                if (tipo.resultado == "ok") {
                    document.getElementById("responseValidarAnuncio").innerHTML = "Anuncio validado com sucesso!!";
                } else {
                    document.getElementById("responseValidarAnuncio").innerHTML = "Erro! Não foi possível validar o anuncio em questão!!";
                }
            });
        }
    });

});