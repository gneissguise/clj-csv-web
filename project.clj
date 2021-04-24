(defproject gr-homework "1.5.0"
  :description "Delimited Flat File Parsing and REST api"
  :url "https://github.com/gneissguise/gr-homework/README.md"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/data.csv "1.0.0"]
                 [org.clojure/tools.cli "1.0.206"]
                 [ring "1.9.2"]
                 [compojure "1.6.1"]
                 [jumblerg/ring-cors "2.0.0"]
                 [org.clojure/data.json "2.2.1"]
                 [ring/ring-json "0.5.1"]]
  :main ^:skip-aot gr-homework.core
  :target-path "target/%s"
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler rest-api.handler/reloadable-app}
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}
             :dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring/ring-mock "0.3.2"]]}})
