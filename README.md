# 👨‍🦱 Inicio de sesión con Github, Facebook y Google 

## Requisitos
<p>
Para poder ejecutar este proyecto se necesita:
</p>

* Tener intalado el XAMPP.   
* Activar apache y MySQL.
* Tener credenciales cliente empresa y cliente secreto de Github, Facebook Y Google.
* Se incluye los datos de esta manera en application.properties:

```
spring.security.oauth2.client.registration.github.client-id=
spring.security.oauth2.client.registration.github.client-secret=
spring.security.oauth2.client.registration.google.client-id=
spring.security.oauth2.client.registration.google.client-secret=
spring.security.oauth2.client.registration.facebook.client-id=
spring.security.oauth2.client.registration.facebook.client-secret=
spring.security.oauth2.client.registration.facebook.scope=email,public_profile
spring.security.oauth2.client.registration.github.scope=user:email
spring.security.oauth2.client.registration.google.scope=email,profile
```
* Se necesita que tengas una base de datos llamada oauth.
* Tener java instalado, minimo versión 17.
* Se necesita tener sdk java.
* Tener en cuanta que es puerto localhost://8085 al tener los credenciales
* Si hará alguna modificación, asegurarse de realizarlos en Maven


# ¿Como tener las credenciales?
* [Credenciales de Google](https://youtu.be/D8DMj2lQMwo?si=uO4cCi_rAOSZKL1V)
* [Credenciales de Facebook apartir del minuto 32:09](https://youtu.be/AOp5TzRM5RY?si=SHtyVSm9q0DHpxfZ)
* [Credenciales de Github](https://youtu.be/R9lxXQcy-nM?si=e0u04su-UgwD6rM9)

## Tabla usuario

| Left columns  | Right columns |
| ------------- |:-------------:|
|     id_user          | int    |
| email      | Varchar(50)     |
| name      | Varcahr(50)    |
|registration_date|datetime(6)|
|status|booleano |


## Como descargarlo

```
git clone https://github.com/KeinerNarvaez/Oauth.git
```

