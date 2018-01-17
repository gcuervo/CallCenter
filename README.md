# Call Center

### Solución cuando no existe empleado disponible

En el caso de que no exista un empleado disponible para atender un llamado, la solución por la que se opto fue la de crear una cola de espera para estas llamadas. Estas son transferidas al primer empleado que se libere.

### Design patterns

Se utilizo el Chain of Responsibility Design Pattern para el modelado de empleados. Cada empleado evalua si puede realizar o no una operacion, si este no puede la delega al siguiente.



