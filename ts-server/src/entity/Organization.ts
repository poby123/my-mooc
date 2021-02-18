import { Entity, Column, PrimaryGeneratedColumn, OneToMany } from 'typeorm';
import { Category } from './Category';
import { Member } from './Member';
@Entity()
export class Organization {
    @PrimaryGeneratedColumn()
    id!: number;

    @Column({ type: "varchar", length: 50 })
    title!: string;

    @OneToMany(() => Category, category => category.organization)
    category!: Category[];

    @OneToMany(() => Member, member => member.organization)
    member!: Member[];
}