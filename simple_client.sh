#!/bin/bash

cat <<EOF | yq eval -o=json - | grpcurl -d @ \
  -import-path ./src/main/proto \
  -proto larky.proto \
  -plaintext \
  localhost:6565 \
  com.moehajj.starlarky.example.StarlarkyServiceExample/Compute
script: |-
   load("@stdlib//builtins", "builtins")
   load("@stdlib//json", "json")
   load("@vgs//vault", "vault")

   def handle(input):
      input_json = json.loads(input)
      secret = input_json["card_number"]
      token = vault.redact(secret)

      input_json["card_number"] = token
      return json.dumps(input_json)
input: '{ "card_number": "4111111111111111" }'
EOF