import json
import sys

import numpy as numpy

import matrices


def arbitrary_matrix2d_to_numpy_array(matrix2d):
    array = list()
    for i in range(matrix2d.h):
        array.append(list())
        for j in range(matrix2d.w):
            array[i].append(round(matrix2d.get(i, j).a))
    return numpy.array(array)


# for reference:
# https://en.wikipedia.org/wiki/Diophantine_equation#System_of_linear_Diophantine_equations
# https://pypi.org/project/smithnormalform/
def main(args):
    if len(args) != 1:
        print("")
        return
    C = numpy.asarray(json.loads(args[0]))
    B = matrices.B_dim
    U = matrices.U_dim
    D = U.dot(C)
    V = matrices.V_dim

    # finalizing the problem
    V_1X = list()
    smallest_size = min(B.shape[0], B.shape[1])
    for i in range(smallest_size):
        V_1X.append(D[i]//B[i][i])

    V_1X_end = list()
    remaining_amount_of_elements_to_add = B.shape[1] - smallest_size
    for i in range(remaining_amount_of_elements_to_add):
        V_1X_end.append(0)

    X, V_1X_end = diophantine_linear_optimize(V, (V_1X, V_1X_end), )

    #X = V.dot(numpy.append(V_1X.copy(), V_1X_end))
    if X.shape[0] == 0:
        print("")
        return

    #X = numpy.asarray(list(map(lambda x: round(x), X))).transpose()


    print("totems:")
    print(matrices.A_dim.dot(X))
    print("should be:")
    print(C)

    # formatting the output
    print("\nblocks:")
    output = str(X[0])
    for i in range(1, len(X)):
        output += ":" + str(X[i])

    print(output)

if __name__ == "__main__":
    main(sys.argv[1:])
