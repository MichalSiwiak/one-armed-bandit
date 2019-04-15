<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>coffeecoding.net</title>
    <meta name="description"
          content="Free open source projects present different java solutions using spring, hibernate and other popular frameworks.">
    <meta name="keywords"
          content="java, spring, hibernate, apache, tomcat, coding, programmer, linux, google cloud platform, open source, bootstrap, mysql, java ideas">
    <!-- CSS dependencies -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
          type="text/css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.standalone.min.css">
    <link rel="stylesheet" href="https://coffeecoding.net/resources/css/now-ui-kit.css" type="text/css">
    <link rel="stylesheet" href="https://coffeecoding.net/resources/css/style.css" type="text/css">
    <link rel="stylesheet" href="resources/css/style.css" type="text/css">
    <link rel="icon" href="resources/img/favicon.png">
    <!-- PAGE scripts -->


    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
    <script src="resources/js/functions.js"></script>

<body ng-app="myApp" ng-controller="myController" class="bg-light text-dark" style="">

<div id="wrap">
    <div id="main" class="clear-top">
        <div class="collapse" id="navbarHeader">
            <div class="container">
                <div class="row">
                    <div class="col-md-7 py-4">
                        <h4>About</h4>
                        <p class="text-info">Free open source projects present different java solutions using spring,
                            hibernate and other popular frameworks.</p>
                    </div>
                    <div class="col-md-3 offset-md-1 py-4">
                        <h4>Contact</h4>
                        <ul class="list-unstyled">
                            <li>
                                <a href="https://pl.linkedin.com/in/michalsiwiak" class="text-secondary"
                                   target="_blank">Follow on LinkedIn</a>
                            </li>
                            <li>
                                <a href="mailto:info@coffeecoding.net" target="_top" class="text-secondary">Email me</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="navbar sticky-top navbar-dark bg-info">
            <div class="container d-flex justify-content-between">
                <a href="https://www.coffeecoding.net/" class="navbar-brand d-flex align-items-center"><i
                        class="fa fa-home fa-2x lead fa-fw d-inline-block" aria-hidden="true"></i>&nbsp;&nbsp;<text
                        class=""> HOME
                </text>
                </a>
                <a href="https://github.com/MichalSiwiak/one-armed-bandit" target="_blank"
                   class="navbar-brand d-flex align-items-center"><i
                        class="fa fa-git-square fa-fw d-inline-block lead fa-2x"></i>&nbsp;&nbsp;<text class="">SOURCE
                    CODE
                </text>
                </a>
                <a href="${pageContext.request.contextPath}" class="navbar-brand d-flex align-items-center"><i
                        class="fa fa-file-text fa-2x fa-fw lead d-inline-block" aria-hidden="true"></i>&nbsp;&nbsp;<text
                        class="">DESCRIPTION
                </text>
                </a>
                <a href="https://coffeecoding.net/resources/img/cv_msiwiak.pdf" target="_blank"
                   class="navbar-brand d-flex align-items-center"><i
                        class="fa fa-address-card fa-2x lead fa-fw d-inline-block" aria-hidden="true"></i>&nbsp;&nbsp;<text
                        class="">RESUME
                </text>
                </a>
                <a href="/contact" class="navbar-brand d-flex align-items-center"><i
                        class="fa fa-envelope fa-2x lead fa-fw d-inline-block" aria-hidden="true"></i>&nbsp;&nbsp;<text
                        class="">CONTACT
                </text>
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader"><span
                        class="navbar-toggler-icon"></span></button>
            </div>
        </div>

        <div class="text-center py-4 bg-secondary"
             style="	background-image: linear-gradient(to left, rgba(0, 0, 0, 0.1), rgba(0, 0, 0, 0.9));	background-position: top left;	background-size: 100%;	background-repeat: repeat;">
            <div class="container">
                <div class="row">
                    <div class="col-md-0 text-left">
                        <h1 class="text-left text-primary">One armed bandit</h1>
                        <p class="lead text-left">This application is a solution for...</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="py-5 mt-3" >
            <div class="container">
                <div class="row">
                    <div class="col-md-3"><button ng-click="startGame()" class="btn w-100 btn-success" type="button">Start Game</button></div>
                    <div class="col-md-3"><button ng-click="cokolwiek()" class="btn w-100 btn-danger" type="button">End Game</button></div>
                    <div class="col-md-3"><button class="btn w-100 btn-warning" type="button">Spin</button></div>
                    <div class="col-md-3"><button class="btn w-100 btn-secondary" type="button">Table</button></div>
                </div>
            </div>
        </div>
        <div class="py-5 mt-3" >
            <div class="container">
               <%-- resources/img/1.png--%>
                {{'resources/img/'+game.symbols[0][0]+'.png'}}
                <br>
                {{'resources/img/'+game.symbols[0][1]+'.png'}}
                <br>
                {{'resources/img/'+game.symbols[0][2]+'.png'}}
            </div>
        </div>
        <div class="py-3">
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group text-center"><label>BET</label><input type="number" class="form-control rounded-0 text-center text-dark"></div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group text-center"><label>WIN</label><input type="number" class="form-control rounded-0 text-center text-dark"></div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group text-center"><label>SUM OF WINS</label><input type="number" class="form-control rounded-0 text-center text-dark"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="py-3 mb-5">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 border"><img class="img-fluid d-block rounded mx-auto" src="{{'resources/img/'+game.symbols[0][0]+'.png'}}" width="200"></div>
                    <div class="col-md-4 border"><img class="img-fluid d-block rounded mx-auto" src="{{'resources/img/'+game.symbols[1][0]+'.png'}}" width="200"></div>
                    <div class="col-md-4 border"><img class="img-fluid d-block rounded mx-auto" src="{{'resources/img/'+game.symbols[2][0]+'.png'}}" width="200"></div>
                </div>
                <div class="row bg-warning border border-dark">
                    <div class="col-md-4 border"><img class="img-fluid d-block rounded mx-auto" src="{{'resources/img/'+game.symbols[0][1]+'.png'}}" width="200"></div>
                    <div class="col-md-4 border"><img class="img-fluid d-block rounded mx-auto" src="{{'resources/img/'+game.symbols[1][1]+'.png'}}" width="200"></div>
                    <div class="col-md-4 border"><img class="img-fluid d-block rounded mx-auto" src="{{'resources/img/'+game.symbols[2][1]+'.png'}}" width="200"></div>
                </div>
                <div class="row">
                    <div class="col-md-4 border"><img class="img-fluid d-block rounded mx-auto" src="{{'resources/img/'+game.symbols[0][2]+'.png'}}" width="200"></div>
                    <div class="col-md-4 border"><img class="img-fluid d-block rounded mx-auto" src="{{'resources/img/'+game.symbols[1][2]+'.png'}}" width="200"></div>
                    <div class="col-md-4 border"><img class="img-fluid d-block rounded mx-auto" src="{{'resources/img/'+game.symbols[2][2]+'.png'}}" width="200"></div>
                </div>
            </div>
        </div>


    </div>
</div>


<footer class="footer bg-dark text-muted">
    <div class="container">
        <p class="float-right">
            <a href="#">Back to top</a>
        </p>
        <p>© Copyright 2018 coffeecoding.net - All rights reserved.<br>Contact: info@coffeecoding.net<br>Warsaw PL<br><a
                href="https://www.coffeecoding.net/">Visit the homepage</a>
        </p>
    </div>
</footer>


</body>
</html>