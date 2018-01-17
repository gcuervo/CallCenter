# Call Center

### Solución cuando no existe empleado libre

Si no existe un empleado libre, la solución por la que se opto fue crear una cola de espera para las llamadas. Estas llamadas son transferidas al primer empleado que se libere.

### Design patterns

Se utilizo el Chain of Responsibility Design Pattern para el modelado de empleados. Cada empleado evalua si puede realizar o no una operacion, si este no puede la delega al siguiente.



