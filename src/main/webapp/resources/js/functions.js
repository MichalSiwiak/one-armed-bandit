var app = angular.module("myApp", []);

app.controller("myController", function ($scope, $http) {

    $scope.game = '';
    $scope.load = true;
    $scope.start = false;

    $scope.form = {
        gameId: "",
        rno: 1
    };

    $scope.startGame = function () {
        $http({
            method: 'GET',
            url: 'startGame'
        }).then(function successCallback(response) {
            $scope.game = response.data;
            $scope.load = false;
            $scope.start = true;
            $scope.form.gameId = response.data.gameId;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    };

    $scope.spin = function () {
        $http({
            method: 'POST',
            url: 'spin',
            data: angular.toJson($scope.form),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function successCallback(response) {
            $scope.game = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    };

    $scope.endGame = function () {
        $http({
            method: 'POST',
            url: 'endGame',
            data: $scope.game.gameId,
            headers: {
                'Content-Type': 'text/plain'
            }
        }).then(function successCallback(response) {
            $scope.game = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    };

});