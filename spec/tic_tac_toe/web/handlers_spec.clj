(ns tic-tac-toe.web.handlers-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.web.handlers :refer :all]
            [tic-tac-toe.board :as board]))

(describe "new-game"
          (it "responds with new game"
              (should= {
                         :board [:_ :_ :_ :_
                                 :_ :_ :_ :_
                                 :_ :_ :_ :_
                                 :_ :_ :_ :_ ] 
                         :current-player { :type :human-web :marker :X }
                         :opponent-player {:type :hard-computer :marker :O}
                         :state "active"
                         :winner nil }
                       (:body (new-game {:params {"opponent" "hard-computer" "size" "4"}})))))

(describe "update-game"
          (it "responds with updated game"
              (should= {
                         :board [:X :_ :_
                                 :_ :_ :_
                                 :_ :_ :_ ] 
                         :current-player { :type :human-web :marker :O }
                         :opponent-player {:type :human-web :marker :X}
                         :state "active"
                         :winner nil }
                       (:body (update-game 
                                {:body {
                         :board ["X" "_" "_"
                                 "_" "_" "_"
                                 "_" "_" "_"] 
                         :current-player {:type "human-web" :marker "X" }
                         :opponent-player {:type "human-web" :marker "O"}
                         :state "active"
                         :winner nil 
                         :move 1 }})))))
