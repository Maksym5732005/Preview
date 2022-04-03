package com.preview.base.data

import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject

abstract class HashMapMemory : MutableMap<String, Any?> by ConcurrentHashMap()

class AppMemory @Inject constructor() : HashMapMemory()