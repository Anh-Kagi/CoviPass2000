# CovidAPI

This project is a website allowing users to schedule appointments
for COVID-19 vaccinations.
The project consists of a backend built using Java with the Spring Framework
and Hibernate, and a frontend built using Angular, Bootstrap, and Angular Material.

## Build & Run

You can run the project using docker-compose by running the following command:

```bash
docker compose up
```

It can also be run outside of docker if a postgres server is running on localhost port 5432,
with `postgres` as username, password, and database name (you can change this by editing the script):

```bash
./start.sh
```

This will build the whole project and run the server in development mode.
In the mode, the database is recreated and destroyed between each start.
It is also populated with a superuser account with the following credentials: `sadmin:sadmin`.

The website will be accessible to http://localhost:8080/.

## Infrastructure

The patient can search for nearby centers and book an appointment. They don't need to login or anything.

The users that can authenticate are the doctors, the center's admins, and the superadmin.

The doctor can seek for appointments and mark them as done.

The admin can manage the center's doctors (he cannot delete a doctor from the db)
and appointments (he can only seek and delete them).

Finally, the superadmin can manage all the users and centers.

The project is split in two parts: the backend and the frontend.
When the project is built, the frontend is built,
and the resulting files are copied to the backend's resources,
for them to be served as static files.

## Team

- Bastien PESME (Anh-Kagi) n°31812375
- Youssef IFRI (Yousssssss) n°31803706
