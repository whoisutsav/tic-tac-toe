(ns tic-tac-toe.web.handler-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.web.handler :refer :all]
            [tic-tac-toe.board :as board]))

(def base-request {:body {
                         :board ["X" "_" "_"
                                 "_" "_" "_"
                                 "_" "_" "_"] 
                         :current-player {:type "human-web" :marker "X" }
                         :opponent-player {:type "human-web" :marker "O"}
                         :state "active"
                         :winner nil 
                         :move 1 }})


(describe "handle-new"
          (it "returns a new game"
              (should= {
                         :board [:_ :_ :_
                                 :_ :_ :_
                                 :_ :_ :_ ] 
                         :current-player { :type :human-web :marker :X }
                         :opponent-player {:type :hard-computer :marker :O}
                         :state "active"
                         :winner nil }
                       (:body (handle-new {:params {"opponent" "hard-computer"}})))))

(describe "handle"
          (it "advances game"
              (should= {
                         :board [:X :_ :_
                                 :_ :_ :_
                                 :_ :_ :_ ] 
                         :current-player { :type :human-web :marker :O }
                         :opponent-player {:type :human-web :marker :X}
                         :state "active"
                         :winner nil }
                       (:body (handle base-request))))
          (it "shows winner"
              (should= {
                         :board [:X :O :X
                                 :X :O :_
                                 :_ :O :X ] 
                         :current-player { :type :human-web :marker :X }
                         :opponent-player {:type :hard-computer :marker :O}
                         :state "win"
                         :winner :O }
                       (:body (handle (-> (assoc-in base-request [:body :board] 
                                                ["X" "O" "X"
                                                 "X" "O" "_"
                                                 "_" "_" "_"])
                                          (assoc-in [:body :opponent-player :type] "hard-computer")
                                          (assoc-in [:body :move] 9)))))))
