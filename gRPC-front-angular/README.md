## Generate ts file from .proto files

add dependency: https://github.com/timostamm/protobuf-ts
```
npm install @protobuf-ts/plugin 
```
### in the package.json file in the script part add: 

```
"generate-ts": "npx protoc --ts_out ./src/generated/proto --proto_path proto proto/request/get/*.proto proto/request/*.proto proto/request/update/*.proto proto/response/*.proto proto/service/*.proto",
"start": " npm run generate-ts && ng serve",
```

put all the proto files in the proto directory
the code above will read all the .proto files from the proto directory where the .proto files are and will generate the
ts files in ./src/generated/proto  ----> don't forget to create first the generated/proto folders first before generating
the ts files.
