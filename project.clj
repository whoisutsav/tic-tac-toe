(defproject tic_tac_toe "0.1.0-SNAPSHOT"
  :description "Tic tac toe for the command line"
  :url "http://github.com/whoisutsav/tic_tac_toe"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main tic-tac-toe.cli-main
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :profiles {:dev {:dependencies [[speclj "3.3.2"]]}}
  :plugins [[speclj "3.3.2"]]
  :test-paths ["spec"])
