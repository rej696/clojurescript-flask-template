(defproject app "0.0.1"
  :description "Initial Test setup for clojurescript"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [binaryage/devtools "0.9.4"]
                 [org.clojure/clojurescript "1.10.238" :scope "provided"]
                 [reagent "0.7.0"]
                 [cljs-http "0.1.46"]]

  :source-paths ["src/cljs"]

  :plugins [[lein-figwheel "0.5.16"]
            [lein-cljsbuild "1.1.7" :exculsions [[org.clojure/clojure]]]]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src/cljs"]
                :figwheel {:on-jsload "app.core/on-js-reload"
                           :open-urls ["http://localhost:3449/index.html"]}
                :compiler {:main app.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/app.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true}}]}

  :figwheel {:css-dirs ["resources/public/css"]}

  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.9"]
                                  [figwheel-sidecar "0.5.16"]
                                  [cider/piggieback "0.3.1"]]
                   :source-path "dev"
                   :clean-targets ^{:protect false} ["resources/public/js/compiled"]}}) 
