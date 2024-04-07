# Development

## Tailwind

``` shell
./tailwindcss-linux-x64 -i input.css -o ./resources/public/css/output.css --watch
```

## Jack In

- Inside `showcase.backend`, evaluate the namespace
- Evaluate the form
   ```clojure
   (def server
    (adapter/run-jetty #'app {:port 8090
                              :join? false}))
   ```
- Browse `http://localhost:8090/`
