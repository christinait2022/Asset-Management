# Asset-Management

#### REST API
```
[GET] /allAsset                        list all asset  
[GET] /Asset/{name_part}               list all asset whose name containing name_part
[POST] /asset                          create new asset
[GET] /asset/{id}                      get an asset by id
[PUT] /asset/{id}                      update an asset by id
[DELETE] /asset/{id}                   delete an asset by id, can not delete when status of the asset is assigned

[GET] /allCategory                     list all categories
[POST] /category                       create new category
[GET] /category/{id}                   get an category by id
[PUT] /category/{id}                   update an category by id
[DELETE] /category/{id}                delete an category by id

[POST] /employee/{employee_id}/lendAsset/{asset_id}     
create a record of lending asset, when someone lend an asset.
  
[POST] /employee/{employee_id}/returnAsset/{asset_id}     
create a record of returning asset, when someone returned an asset.

[POST] /employee/{employee_id}/CheckAsset/{asset_id}      
create a record of Checking asset, when someone has checked the returned asset.
```