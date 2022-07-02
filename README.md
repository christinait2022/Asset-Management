# Asset-Management

#### REST API

```
[GET] /allAsset                        list all asset.
[GET] /allAsset?name=[keyword]         list all asset which name contains keyword.
[POST] /asset                          create an new asset.
[GET] /asset/{id}                      get an asset by id.
[PUT] /asset/{id}                      update an asset by id.
[DELETE] /asset/{id}                   delete an asset by id, can not delete when status of the asset is assigned.

[GET] /allCategory                     list all categories.
[POST] /category                       create an new category.
[GET] /category/{id}                   get an category by id.
[PUT] /category/{id}                   update an category by id.
[DELETE] /category/{id}                delete an category by id.

[POST] /lendAsset/{employee_id}/{asset_id}     
create a record of the asset being lent, when someone lends an asset.
  
[POST] /returnAsset/{employee_id}/{asset_id}     
create a return asset record, when someone returns an asset.

[POST] /CheckAsset/{employee_id}/{asset_id}      
create a record of the asset being inspected, when someone inspects the returned asset.
```