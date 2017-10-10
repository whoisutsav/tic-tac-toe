(ns tic-tac-toe.core.player.computer-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.core.player :refer [get-move]]
            [tic-tac-toe.core.player.computer :refer :all])) 

(describe "get-move"
          (around [it] (with-out-str (it)))

          (it "returns an empty space"
              (should= 4 (get-move {:board [:x :o :x
                                            :_ :o :x
                                            :x :x :o ] 
                                    :current-player {:type :computer} 
                                    :opponent-player nil}))))

