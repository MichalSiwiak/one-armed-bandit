var app = angular.module("myApp", []);

app.controller("myController", function ($scope, $http) {

    $scope.game = '';

    $scope.startGame = function () {
        $http({
            method: 'GET',
            url: 'startGame'
        }).then(function successCallback(response) {
            $scope.game = response.data;
            console.log(response.data);
            console.log("cokolwiek");
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    };

    $scope.cokolwiek = function () {
        console.log('dupa');
    };

});