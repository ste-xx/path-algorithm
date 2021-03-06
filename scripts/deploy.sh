#!/usr/bin/env bash

set -e # halt script on error
zip -r website.zip web external index.html xkcd-script.woff

curl -H "Content-Type: application/zip" \
     -H "Authorization: Bearer $NETLIFYKEY" \
     --data-binary "@website.zip" \
     https://api.netlify.com/api/v1/sites/amazing-wright-416994.netlify.com/deploys