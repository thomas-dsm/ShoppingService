# SHOPPING SERVICE

## Projet Cloud Licence Pro Web

Made By Thomas Da Silva Mendonca
With Adrien Coudour

Web Service avec Heroku

ShoppingService : 
* appeler par des clients
* renvoie l'etat des stocks des livres
* commande les livres si ils sont disponible

Appelle le service StockService si `isbn` de `Book` est valide
 
Lien vers le service : [Shopping Service](https://shopping-service.herokuapp.com/ "Shopping Service")


|Class |Book |
|--- |--- |
|int |id |
|String |isbn |
|String |title |
|String |author |
|Date |date |

|Class |Customer |
|--- |--- |
|int |idCustomer |
|String |name |
|String |firstname |

|Class |Store |
|--- |--- |
|int |idStore |
|String |account |
|String |nameStore |