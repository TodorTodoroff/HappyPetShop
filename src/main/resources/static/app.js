let loadUsersButton = document.getElementById('loadUsers')

loadUsersButton.addEventListener('click', onLoadUsers);

function onLoadUsers(event) {
    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    let usersContainer = document.getElementById('users-container')
    usersContainer.innerHTML = ''

    fetch("http://localhost:8080/api/users", requestOptions)
        .then(response => response.json())
        .then(json => json.forEach(user => {
            // here we will create some elements and add them to the table.
            let row = document.createElement('tr')

            let emailCol = document.createElement('td')
            let firstNameCol = document.createElement('td')
            let lastNameCol = document.createElement('td')
            let userRolesCol = document.createElement('td')
            let button = document.createElement("button")

            emailCol.textContent = user.email
            firstNameCol.textContent = user.firstName
            lastNameCol.textContent = user.lastName
            var output = ''
            let isAdmin = false
            for (var ur in user.userRoles) {
                if (user.userRoles[ur].userRole === 'ADMIN'){
                    isAdmin = true
                }
               output += user.userRoles[ur].userRole + ' '
            }
            userRolesCol.textContent = output
            button.textContent = isAdmin ? 'Admin OFF' : 'Admin ON'

            button.onc

            // add the columns to the parent row
            row.appendChild(emailCol)
            row.appendChild(firstNameCol)
            row.appendChild(lastNameCol)
            row.appendChild(userRolesCol)
            row.appendChild(button)

            usersContainer.appendChild(row);
        }))
        .catch(error => console.log('error', error));
}