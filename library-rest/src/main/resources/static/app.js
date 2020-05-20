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
        if ($scope.user != null && $scope.user.firstName && $scope.user.lastName && $scope.user.email && $scope.user.password) {
            UserCRUDService.addUser($scope.user.firstName, $scope.user.lastName, $scope.user.email, $scope.user.password)
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
            $scope.errorMessage = 'Please enter first name, last name, email and password!';
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

app.controller('BookController',  ['$scope','BookService', function ($scope,BookService) {
    $scope.getBook = function () {
            var id = $scope.book.id;
            BookService.getBook($scope.book.id)
                .then(function success(response){
                        $scope.book = response.data;
                        $scope.book.id = id;
                        $scope.message='';
                        $scope.errorMessage = '';
                    },
                    function error (response ){
                        $scope.message = '';
                        if (response.status === 404){
                            $scope.errorMessage = 'Book not found!';
                        }
                        else {
                            $scope.errorMessage = "Error getting book!";
                        }
                    });
    }

    $scope.addBook = function () {
            if ($scope.book != null && $scope.book.title && $scope.book.author) {
                BookService.addBook($scope.book.title, $scope.book.author)
                    .then (function success(response){
                            $scope.message = 'Book added!';
                            $scope.errorMessage = '';
                        },
                        function error(response){
                            $scope.errorMessage = 'Error adding book!';
                            $scope.message = '';
                        });
            }
            else {
                $scope.errorMessage = 'Please enter title and author!';
                $scope.message = '';
            }
    }

    $scope.deleteBook = function () {
            BookService.deleteBook($scope.book.id)
                .then (function success(response){
                        $scope.message = 'Book deleted!';
                        $scope.book = null;
                        $scope.errorMessage='';
                    },
                    function error(response){
                        $scope.errorMessage = 'Error deleting book!';
                        $scope.message='';
                    })
    }

    $scope.getAllBooks = function () {
            BookService.getAllBooks()
                .then(function success(response){
                        $scope.books = response.data;
                        $scope.message = '';
                        $scope.errorMessage = '';
                    },
                    function error (response ){
                        $scope.message='';
                        $scope.errorMessage = 'Error getting books!';
                    });
    }

    $scope.findByAuthor = function () {
            BookService.findByAuthor($scope.book.author)
                .then(function success(response){
                    $scope.books = response.data;
                    $scope.message = $scope.books;
                    $scope.errorMessage = '';
                },
                function error(response){
                    $scope.message = '';
                    $scope.errorMessage = 'Error getting books!';
                });

    }
}]);

app.service('UserCRUDService',['$http', function ($http) {

    this.getUser = function getUser(userId){
        return $http({
            method: 'GET',
            url: 'rest/user_id/'+userId
        });
    }

    this.addUser = function addUser(firstName, lastName, email, passwordHash){
        return $http({
            method: 'POST',
            url: 'rest/users',
            data: {firstName:firstName, lastName:lastName, email:email, passwordHash:passwordHash}
        });
    }

    this.deleteUser = function deleteUser(id){
        return $http({
            method: 'DELETE',
            url: 'rest/delete/user/'+id
        })
    }

    this.updateUser = function updateUser(id, firstName, lastName, email){
        return $http({
            method: 'PATCH',
            url: 'rest/user_id/'+id,
            data: {firstName:firstName, lastName:lastName, email:email}
        })
    }

    this.getAllUsers = function getAllUsers(){
        return $http({
            method: 'GET',
            url: 'rest/users'
        });
    }

}]);

app.service('BookService',['$http', function ($http) {
    this.getBook = function getBook(bookId){
            return $http({
                method: 'GET',
                url: 'rest/book_id/'+bookId
            });
    }

    this.addBook = function addBook(title, author){
            return $http({
                method: 'POST',
                url: 'rest/books',
                data: {title:title, author:author}
            });
    }

    this.deleteBook = function deleteBook(id){
            return $http({
                method: 'DELETE',
                url: 'rest/delete/book/'+id
            })
    }

    this.getAllBooks = function getAllBooks(){
            return $http({
                method: 'GET',
                url: 'rest/books'
            });
    }

    this.findByAuthor = function findByAuthor(author) {
            return $http({
                method: 'GET',
                url: 'rest/books_author/'+author
            });
    }
}]);

app.controller('LoanController',  ['$scope','LoanService', function ($scope,LoanService) {

    $scope.getAllLoans = function () {
        LoanService.getAllLoans()
            .then(function success(response){
                    $scope.loans = response.data;
                    $scope.message = '';
                    $scope.errorMessage = '';
                },
                function error (response ){
                    $scope.message='';
                    $scope.errorMessage = 'Error getting loans!';
                });
    }

    $scope.getLoan = function () {
        if ($scope.loan != null && $scope.loan.id) {
            var id = $scope.loan.id;
            LoanService.getLoan($scope.loan.id)
                .then(function success(response) {
                        $scope.loan = response.data;
                        $scope.loan.id = id;
                        $scope.message = '';
                        $scope.errorMessage = '';
                    },
                    function error(response) {
                        $scope.message = '';
                        if (response.status === 404) {
                            $scope.errorMessage = 'Loan not found!';
                        } else {
                            $scope.errorMessage = "Error getting loan!";
                        }
                    })
        }
        else {
                $scope.errorMessage = 'Please enter loan id!';
                $scope.message = '';
        };
    }

    $scope.deleteLoan = function () {
        if ($scope.loan != null && $scope.loan.id) {
            LoanService.deleteLoan($scope.loan.id)
                .then(function success(response) {
                        $scope.message = 'Loan deleted!';
                        $scope.loan = null;
                        $scope.errorMessage = '';
                    },
                    function error(response) {
                        $scope.errorMessage = 'Error deleting loan!';
                        $scope.message = '';
                    })
        } else {
            $scope.errorMessage = 'Please enter loan id!';
            $scope.message = '';
        }
    };

    $scope.addLoan = function () {
        if ($scope.loan != null && $scope.loan.book_id && $scope.loan.user_id && $scope.loan.registeredAt) {
            LoanService.addLoan($scope.loan.book_id, $scope.loan.user_id, $scope.loan.registeredAt, $scope.loan.returnedAt, $scope.loan.returnCondition)
                .then (function success(response){
                        $scope.message = 'Loan added!';
                        $scope.errorMessage = '';
                    },
                    function error(response){
                        $scope.errorMessage = 'Error adding loan!';
                        $scope.message = '';
                    });
        }
        else {
            $scope.errorMessage = 'Please enter Borrowed at, Loaned book ID and Loaned user ID';
            $scope.message = '';
        }
    }

}]);

app.service('LoanService',['$http', function ($http) {

    this.getAllLoans = function getAllLoans(){
        return $http({
            method: 'GET',
            url: 'rest/loans'
        });
    }

    this.getLoan = function getLoan(loanId){
        return $http({
            method: 'GET',
            url: 'rest/loan_id/'+loanId
        });
    }

    this.deleteLoan = function deleteLoan(id){
        return $http({
            method: 'DELETE',
            url: 'rest/delete/loan/'+id
        })
    }

    this.addLoan = function addLoan(book_id, user_id, registeredAt, returnedAt, returnCondition){

        var book = {id:id};
        book.id = book_id;

        var user = {id:id};
        user.id = user_id;

        return $http({
            method: 'POST',
            url: 'rest/loans',
            data: {book:book, user:user, registeredAt:registeredAt, returnedAt:returnedAt, returnCondition:returnCondition}
        });
    }

}]);