@startuml
title API Entreprises - Spécification des échanges REST
 
actor "Client" as Client
participant "API REST\nEntreprises" as API
participant "Service\nEntreprise" as Service
database "Base de données\nSIRENE locale" as DB
 
== Retrouver une société par son identifiant ==
 
Client -> API : GET /v1/api/company/{siren}
API -> Service : getCompanyBySiren(siren)
Service -> DB : SELECT entreprise\nWHERE siren = ?
DB --> Service : Entreprise trouvée / aucune
Service --> API : Company
 
alt Entreprise trouvée
    API --> Client : 200 OK\nCompany JSON
else Entreprise inexistante
    API --> Client : 404 Not Found
end
 
 
== Chercher des entreprises par code activité ==
 
Client -> API : GET /v1/api/company/search?code=?
API -> Service : getCompanyByName(code)
Service -> DB : SELECT entreprises\nWHERE codeActivite = ?
DB --> Service : getCompanyByActivityCode(activityCode)
Service --> API : List Companies
API --> Client : 200 OK\nListe JSON
alt Entreprise trouvée
    API --> Client : 200 OK\nCompany JSON
else Entreprise inexistante
    API --> Client : 404 Not Found
end
 
== Chercher une entreprise par dénomination ==
 
Client -> API : GET /v1/api/company/search?name=?
API -> Service : getCompanyByName(name)
Service -> DB : SELECT entreprises\nWHERE denomination = ?
DB --> Service : 
Service --> API : Company
API --> Client : 200 OK\nListe JSON
alt Entreprise trouvée
    API --> Client : 200 OK\nCompany JSON
else Entreprise inexistante
    API --> Client : 404 Not Found
end 
 
== Modifier une entreprise ==
 
Client -> API : PATCH /v1/api/company/{siren}\nDonnées JSON à modifier
API -> Service : patchCompany(siren, request)
Service -> DB : Vérifier existence
 
alt Entreprise trouvée
    DB --> Service : Entreprise
    Service -> DB : UPDATE entreprise
    DB --> Service : Modification enregistrée
    Service --> API : Entreprise modifiée
    API --> Client : 200 OK\nEntreprise JSON
else Entreprise inexistante
    DB --> Service : Aucune entreprise
    Service --> API : Introuvable
    API --> Client : 404 Not Found
end
 
 
== Supprimer une entreprise ==
 
Client -> API : DELETE /v1/api/company/{siren}
API -> Service : deleteCompany(siren)
Service -> DB : Vérifier existence
 
alt Entreprise trouvée
    DB --> Service : Entreprise
    Service -> DB : DELETE entreprise
    DB --> Service : Suppression effectuée
    Service --> API : Suppression OK
    API --> Client : 204 No Content
else Entreprise inexistante
    DB --> Service : Aucune entreprise
    Service --> API : Introuvable
    API --> Client : 404 Not Found
end
 
 
@enduml