(defproject tic-tac-toe "0.1.0-SNAPSHOT"
  :description "Clojure Tic Tac Toe"
  :url "http://github.com/whoisutsav/tic-tac-toe"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main tic-tac-toe.core-console
  :dependencies [[org.clojure/clojure "1.8.0"]] 
  :profiles {:dev {:dependencies [[speclj "3.3.2"]]}
             :web {:dependencies [[ring/ring-core "1.5.0"]
                                  [ring/ring-jetty-adapter "1.5.0"]
                                  [ring/ring-json "0.4.0"]]
                   :main tic-tac-toe.core-web}}
  :plugins [[speclj "3.3.2"]]
  :test-paths ["spec"])
