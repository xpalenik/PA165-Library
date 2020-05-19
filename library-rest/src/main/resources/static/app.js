var app = angular.module('app',[]);

app.controller('UserCRUDCtrl', ['$scope','UserCRUDService', function ($scope,UserCRUDService) {

    // UPDATES ONLY EMAIL!!!
    $scope.updateUser = function () {
        UserCRUDService.updateUser($scope.user.id, $scope.user.email)
            .then(function success(response){
                    $scope.message = 'User data updated!';
                    $scope.errorMessage = '';
                },
                function error(response){
                    $scope.errorMessage = 'Error updating user!';
                    $scope.message = '';
                });
    }

    $scope.getUser = function () {
        var id = $scope.user.id;
        UserCRUDService.getUser($scope.user.id)
            .then(function success(response){
                    $scope.user = response.data;
                    $scope.user.id = id;
                    $scope.message='';
                    $scope.errorMessage = '';
                },
                function error (response ){
                    $scope.message = '';
                    if (response.status === 404){
                        $scope.errorMessage = 'User not found!';
                    }
                    else {
                        $scope.errorMessage = "Error getting user!";
                    }
                });
    }

    $scope.addUser = function () {
        if ($scope.user != null && $scope.user.firstName && $scope.user.lastName && $scope.user.email) {
            UserCRUDService.addUser($scope.user.firstName, $scope.lastName, $scope.user.email)
                .then (function success(response){
                        $scope.message = 'User added!';
                        $scope.errorMessage = '';
                    },
                    function error(response){
                        $scope.errorMessage = 'Error adding user!';
                        $scope.message = '';
                    });
        }
        else {
            $scope.errorMessage = 'Please enter first name, last name and email!';
            $scope.message = '';
        }
    }

    $scope.deleteUser = function () {
        UserCRUDService.deleteUser($scope.user.id)
            .then (function success(response){
                    $scope.message = 'User deleted!';
                    $scope.user = null;
                    $scope.errorMessage='';
                },
                function error(response){
                    $scope.errorMessage = 'Error deleting user!';
                    $scope.message='';
                })
    }

    $scope.getAllUsers = function () {
        UserCRUDService.getAllUsers()
            .then(function success(response){
                    $scope.users = response.data;
                    $scope.message='';
                    $scope.errorMessage = '';
                },
                function error (response ){
                    $scope.message='';
                    $scope.errorMessage = 'Error getting users!';
                });
    }

}]);

app.service('UserCRUDService',['$http', function ($http) {

    this.getUser = function getUser(userId){
        return $http({
            method: 'GET',
            url: 'pa165/rest/users/'+userId
        });
    }

    this.addUser = function addUser(firstName, lastName, email){
        return $http({
            method: 'POST',
            url: 'pa165/rest/users',
            data: {firstName:firstName, lastName:lastName, email:email}
        });
    }

    this.deleteUser = function deleteUser(id){
        return $http({
            method: 'DELETE',
            url: 'pa165/rest/users/'+id
        })
    }

    this.updateUser = function updateUser(id, firstName, lastName, email){
        return $http({
            method: 'PATCH',
            url: 'pa165/rest/users/'+id,
            data: {firstName:firstName, lastName:lastName, email:email}
        })
    }

    this.getAllUsers = function getAllUsers(){
        return $http({
            method: 'GET',
            url: 'pa165/rest/users'
        });
    }

}]);