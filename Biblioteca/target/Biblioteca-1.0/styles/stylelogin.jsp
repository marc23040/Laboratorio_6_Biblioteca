<style>
    @import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');

    *,
    ::after,
    ::before {
        box-sizing: border-box;
    }


    body {
  
        font-family: 'Poppins', sans-serif;
        margin: 0;
    }

    /*menu desplegable*/
    #intro {
        background-color: #1f0808; 
        min-width: 250px;
        max-width: 300px;
        padding: 2%;

    }

    .titulo-opciones{
        font-family: 'Poppins', sans-serif;
        font-size: 200%;
        padding: 10px;
        color: #fff5db;
        margin-top: 8px;

    }

    /* barra de navegacion */
    .navbar {
        background-color: #5e3824;
         border-radius: 1.3rem;
        box-shadow: 0 30px 40px -20px #615145;
    }
    .navbar-toggler-icon {
        background-color: #f8f1e9;
        border-radius: 10%;
    }
    .navbar-toggler{
        color:#340a0b;
    }
    /* opciones navbar */
    .navbar-nav a.nav-link {
        color: #f8f1e9;
    }

    .navbar-nav a.nav-link:hover {
        border-radius: 10px;
        background-color: #7b3b3b;
        color: #f8f1e9;
    }

    /*boton para barra lateral*/
    .btn.icono{
        color: #f8f1e9;
    }

    .btn.icono:hover{
        border-radius: 10px;
        background-color: #7b3b3b;
        color: #f8f1e9;
    }

    /* titulo navbar */
    .navbar-brand {
        padding: 10px;
        color: #fff5db;
        margin-top: 8px;

    }
    .navbar-brand:hover {
        border-radius: 10px;
        color: #fff5db;
    }

    /* boton de buscar */
    .btn.btn-outline-success {
        background-color: #a86b4c;
        border-color: #a86b4c;
        color: #fff;
    }

    .btn.btn-outline-success:hover {
        background-color: #dba488;
        border-color: white;
    }

    /*boton log out*/
    .btn.btn-outline-salir {
        background-color: #c84648;
        border-color: #c84648;
        color: #fff;
        margin-top: 20%;
    }

    .btn.btn-outline-salir:hover{
        background-color: #d31900;
        border-color: white;
        color: #fff;
        margin-top: 20%;

    }

    /*cajas contenedoras de informacion */
    .card{
        box-shadow: rgba(0, 0, 0, 0.07) 0px 1px 2px, rgba(0, 0, 0, 0.07) 0px 2px 4px, rgba(0, 0, 0, 0.07) 0px 4px 8px, rgba(0, 0, 0, 0.07) 0px 8px 16px, rgba(0, 0, 0, 0.07) 0px 16px 32px, rgba(0, 0, 0, 0.07) 0px 32px 64px;
    }


    a {
        cursor: pointer;
        text-decoration: none;
        font-family: 'Poppins', sans-serif;
    }

    /*botones de acciones*/

    .btn.btn-primary {
        background-color: #333333;
        color: #FFFFFF;
        border-color: white;
    }

    .btn.btn-primary:hover {
        background-color: #4d6160;
        color: #FFFFFF;
        border-color: #333333;
        border-width: 1px;
    }

    .btn.submit-acciones {
        background-color: #333333;
        color: #FFFFFF;
        border-color: white;
        margin-left: 1%;
        margin-right: 1%;
    }

    .btn.submit-acciones:hover {
        background-color: #4d6160;
        color: #FFFFFF;
        border-color: #333333;
        border-width: 1px;
    }

    /*boton eliminar*/
    .btn.btn-danger {
        background-color: #c84648;
        border-color: #c84648;
        color: #fff;
    }

    .btn.btn-danger:hover {
        background-color: #d31900;
        border-color: #9e0c39;
        color: #fff;
        border-width: 1px;
    }

    .form-check-label{
        margin-bottom: 10%;
    }
    input[type="checkbox"] {

        background-color: #e2ddd9;
    }

    input[type="checkbox"]:checked {
        background-color: #333333;
    }

    /* Responsive */

    @media (min-width:1025px) {
        .content {
            width: auto;
        }
    }


</style>
