(defproject gr-homework "0.2.0"
  :description "CSF File Parsing"
  :url "https://github.com/gneissguise/gr-homework/blob/data-file-parser/README.md"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/data.csv "1.0.0"]]
  :main ^:skip-aot gr-homework.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
