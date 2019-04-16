var app = angular.module("sessionManagement", []);

app.controller("sessionManagementController", function ($scope, $http) {

    $scope.activeGames = [];

    findAll();

    $scope.deleteGame = function (game) {
        $http({
            method: 'DELETE',
            url: 'activeGames/' + game.gameId
        }).then(function successCallback(response) {
            success();
            error(response);
        })
    };

    function findAll() {
        $http({
            method: 'GET',
            url: 'activeGames'
        }).then(function successCallback(response) {
            $scope.activeGames = response.data;
            console.log(response.data);
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    function success() {
        findAll();
    }

    function error(response) {
        console.log(response.statusText);
    }

});