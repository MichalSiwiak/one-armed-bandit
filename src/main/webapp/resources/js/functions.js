var app = angular.module("myApp", []);

app.controller("myController", function ($scope, $http) {

    $scope.game = '';
    $scope.load = true;
    $scope.start = false;

    $scope.startGame = function () {
        $http({
            method: 'GET',
            url: 'startGame'
        }).then(function successCallback(response) {
            $scope.game = response.data;
            $scope.load = false;
            $scope.start = true;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    };

    $scope.cokolwiek = function () {
        console.log('dupa');
    };

});