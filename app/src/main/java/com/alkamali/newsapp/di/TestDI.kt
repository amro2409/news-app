package com.alkamali.newsapp.di

import dagger.Component
import javax.inject.Inject

@Component
 interface ShapeComponent{
     fun createNewShape(): Shape
 }

class Shape @Inject constructor(rectangle: Rectangle?, circle: Circle?, square: Square?) {
    fun draw() {
        print("Drawing now...")
    }
}

class Rectangle @Inject constructor()
class Square @Inject constructor()
class Circle @Inject constructor()

